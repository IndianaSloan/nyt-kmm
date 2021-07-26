package com.ciaransloan.nytkmm.presentation

sealed class AppState {
    object LoggedIn: AppState()
    object LoggedOut : AppState()
}