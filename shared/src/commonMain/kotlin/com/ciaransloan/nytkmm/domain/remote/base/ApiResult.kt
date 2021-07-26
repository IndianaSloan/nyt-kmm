package com.ciaransloan.nytkmm.domain.remote.base

internal sealed class ApiResult<out T> {
    class Success<out T>(val data: T) : ApiResult<T>()
    class Failure(val throwable: Throwable, val message: String? = null) : ApiResult<Nothing>()
}