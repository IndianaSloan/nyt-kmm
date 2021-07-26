package com.ciaransloan.nytkmm.domain.remote.base

import io.ktor.utils.io.errors.*

internal abstract class BaseApi {

    suspend fun <T : Any> apiCall(call: suspend () -> T): ApiResult<T> {
        return try {
            val response = call.invoke()
            apiResult(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> ApiResult.Failure(t) // network error
                else -> ApiResult.Failure(t, t.message)
            }
        }
    }

    // TODO: Review error handling catching 401, 403 etc...
    private fun <T : Any> apiResult(response: T): ApiResult<T> {
        return ApiResult.Success(response)
    }
}