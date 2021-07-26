package com.ciaransloan.nytkmm.domain.repository.base

import io.ktor.utils.io.errors.*

/**
 * RepositoryError are made for the presentation layer by Repositories and they hide away the obscure
 * complexities of network errors, server errors, unexpected api exceptions...
 */
internal sealed class RepositoryError(cause: Throwable, message: String? = null) :
    Throwable(message, cause) {

    /**
     * It means the operation failed due to internet connectivity issues.
     */
    class Network(cause: Throwable) : RepositoryError(cause, null)

    /**
     * It means the server got the request and responded with an error.
     *
     * @param message the result of various attempts to coax a human readable error message out
     * of the server response. A null value means this coaxing failed (unknown or missing error format).
     */
    class Server(cause: Throwable, message: String? = null) : RepositoryError(cause, message)

    /**
     * Used both to indicate that username/password is incorrect during login, or that the user's
     * session became invalid. Usually means user needs to be shown the login screen.
     */
    class Auth(cause: Throwable, message: String? = null) : RepositoryError(cause, message)

    /**
     * Unexpected/unrecognised failures. As app development advances, these errors should be rarer and rarer.
     */
    class Generic(cause: Throwable, message: String? = null) : RepositoryError(cause, message)

    companion object {
        fun fromException(e: Throwable, message: String? = null): RepositoryError {
            return when (e) {
                // is HttpException -> Server(e, message) // TODO
                is IOException -> Network(e)
                else -> Generic(e, message)
            }
        }
    }
}