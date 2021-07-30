package com.ciaransloan.nytkmm.android.ui.bookmarks

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookmarksScreen() {
    val viewModel: BookmarksViewModel = hiltViewModel()
    viewModel.test()
    Text(text = "Coming Soon!")
}