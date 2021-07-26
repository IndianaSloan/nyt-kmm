package com.ciaransloan.nytkmm

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class AppDispatchers {
    actual val main: CoroutineDispatcher
        get() = Dispatchers.Main
    actual val io: CoroutineDispatcher
        get() = Dispatchers.IO
}