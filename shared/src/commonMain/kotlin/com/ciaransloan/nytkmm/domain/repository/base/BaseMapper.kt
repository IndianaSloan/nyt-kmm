package com.ciaransloan.nytkmm.domain.repository.base

import com.ciaransloan.nytkmm.domain.remote.base.ApiResult

/**
 * There's some boilerplate code needed to convert [ApiResult] into [RepositoryResult].
 * This base class takes care of that.
 */
internal abstract class BaseMapper {

    /**
     * @param callName for logging
     */
    fun <T, R> mapApiResult(
        apiCall: ApiResult<T>,
        callName: String,
        modelMapper: (T) -> R
    ): RepositoryResult<R> {
        return map(apiCall, callName) { modelMapper(it.data) }
    }

    private fun <T, R> map(
        apiResult: ApiResult<T>,
        callName: String,
        unpack: (ApiResult.Success<T>) -> R?
    ): RepositoryResult<R> {
        return when (apiResult) {
            is ApiResult.Success -> mapPayload(unpack(apiResult), callName)
            is ApiResult.Failure -> mapError(apiResult)
        }
    }

    /**
     * @param callName for logging
     */
    private fun <T> mapPayload(payload: T?, callName: String): RepositoryResult<T> {
        return RepositoryResult.success(
            payload ?: throw ApiMappingException("Missing payload from $callName call")
        )
    }

    private fun <T> mapError(apiResult: ApiResult.Failure): RepositoryResult<T> {
        return RepositoryResult.error(
            RepositoryError.fromException(apiResult.throwable, apiResult.message)
        )
    }
}