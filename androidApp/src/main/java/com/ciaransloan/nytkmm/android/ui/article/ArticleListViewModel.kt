package com.ciaransloan.nytkmm.android.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ArticleListViewModel(section: NewsSection? = null) : ViewModel(), KoinComponent {

    private val stateManager: ArticleStateManager by inject()

    init {
        stateManager.getArticles(section)
    }

    val uiState = stateManager.observeState()

    class Factory(private val section: NewsSection? = null) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ArticleListViewModel(section) as T
        }
    }
}