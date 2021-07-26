package com.ciaransloan.nytkmm

import kotlinx.coroutines.*
import platform.Foundation.NSRunLoop
import platform.darwin.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

actual class AppDispatchers {
    actual val main: CoroutineDispatcher
        get() = Dispatchers.Main
    actual val io: CoroutineDispatcher
        get() = Dispatchers.Main
}