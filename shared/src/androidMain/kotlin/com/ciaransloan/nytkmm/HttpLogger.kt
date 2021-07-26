package com.ciaransloan.nytkmm

import android.util.Log
import io.ktor.client.features.logging.*

actual class HttpLogger : Logger {
    override fun log(message: String) {
        Log.i("AndroidHttpLogger", message)
    }
}