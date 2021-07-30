package com.ciaransloan.nytkmm.android.ui.bookmarks

import androidx.lifecycle.ViewModel
import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.section.SectionListStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel
@Inject constructor(private val stateManager: ArticleStateManager) : ViewModel() {

    fun test() {
        stateManager.getBookmarks()
    }
}