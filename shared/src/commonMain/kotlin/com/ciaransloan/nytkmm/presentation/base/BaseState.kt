package com.ciaransloan.nytkmm.presentation.base

sealed class BaseState {
    object Loading : BaseState()
    object Empty : BaseState()
}