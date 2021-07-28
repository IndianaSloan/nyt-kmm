package com.ciaransloan.nytkmm.android.ui.article

import androidx.lifecycle.ViewModel
import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel
@Inject constructor(private val stateManager: ArticleStateManager) : ViewModel() {

    val uiState = stateManager.observeState()

    fun setSection(section: SectionUIModel?) {
        stateManager.getArticles(section)
    }

    fun onPageScrolledToEnd() = stateManager.loadNextPage()
}