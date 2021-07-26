package com.ciaransloan.nytkmm.presentation.base

import com.ciaransloan.nytkmm.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseStateManager {

    private val dispatchers = AppDispatchers()

    internal fun launchCoroutineScope(block: suspend () -> Unit) {
        CoroutineScope(dispatchers.io).launch {
            block.invoke()
        }
    }
}