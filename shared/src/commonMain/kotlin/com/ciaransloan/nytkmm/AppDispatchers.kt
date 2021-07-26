package com.ciaransloan.nytkmm

import kotlinx.coroutines.CoroutineDispatcher

expect class AppDispatchers() {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}