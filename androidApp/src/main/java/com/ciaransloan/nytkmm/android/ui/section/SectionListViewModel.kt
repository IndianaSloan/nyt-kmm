package com.ciaransloan.nytkmm.android.ui.section

import androidx.lifecycle.ViewModel
import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.section.SectionListStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionListViewModel
@Inject constructor(private val stateManager: SectionListStateManager) : ViewModel() {

    val uiState = stateManager.observeState()

    fun getSections() {
        stateManager.getSections()
    }
}