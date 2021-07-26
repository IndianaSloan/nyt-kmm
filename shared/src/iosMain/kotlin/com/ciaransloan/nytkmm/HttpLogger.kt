package com.ciaransloan.nytkmm

import io.ktor.client.features.logging.*

actual class HttpLogger : Logger {
    override fun log(message: String) {
        println("iOSHttpLogger: $message")
    }
}