package com.ciaransloan.nytkmm.domain.repository.base

/**
 * Used by Repositories to return data. Can be either data of type [T] or [RepositoryError].
 * Use it like this:
 *
 * ```
 * val (result, error) = someRepository.doSomeOperation(...)
 * result?.let {
 *    // handle the result
 * }
 * error?.let {
 *    // handle the error
 * }
 * ```
 *
 * Only one of `result` and `data` is ever null.
 */
internal class RepositoryResult<T> private constructor() {

    var result: T? = null
        private set

    operator fun component1(): T? {
        return this.result
    }

    var error: RepositoryError? = null
        private set

    operator fun component2(): RepositoryError? {
        return this.error
    }

    companion object {
        fun <T> success(result: T) = RepositoryResult<T>().apply {
            this.result = result
        }

        fun <T> error(error: RepositoryError) = RepositoryResult<T>().apply {
            this.error = error
        }
    }
}

/**
 * If the context RepositoryResult is a success, map T into R, otherwise just propagate the error
 */
internal suspend fun <T, R> RepositoryResult<T>.flatMapSuspend(mapper: suspend (T) -> RepositoryResult<R>): RepositoryResult<R> {
    val (data, error) = this
    return data
        ?.let {
            try {
                mapper(it)
            } catch (t: Throwable) {
                RepositoryResult.error(
                    RepositoryError.fromException(
                        t,
                        "Something went wrong. Please try again."
                    )
                )
            }
        }
        ?: run { RepositoryResult.error(error!!) }
}

internal fun <T> RepositoryResult<T>.onSuccess(block: (T) -> Unit): RepositoryResult<T> {
    val (success, _) = this
    success?.let { block(it) }
    return this
}

internal fun <T> RepositoryResult<T>.onError(block: (RepositoryError) -> Unit): RepositoryResult<T> {
    val (_, error) = this
    error?.let { block(it) }
    return this
}