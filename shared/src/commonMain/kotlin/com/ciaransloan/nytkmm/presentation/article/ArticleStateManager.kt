package com.ciaransloan.nytkmm.presentation.article

import com.ciaransloan.nytkmm.domain.repository.NytRepositoryContract
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import com.ciaransloan.nytkmm.domain.repository.base.onError
import com.ciaransloan.nytkmm.domain.repository.base.onSuccess
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.ciaransloan.nytkmm.domain.repository.model.defaultSection
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState
import com.ciaransloan.nytkmm.presentation.base.BaseStateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ArticleStateManager : BaseStateManager(), KoinComponent {

    private val repository by inject<NytRepositoryContract>()
    private val uiState = MutableStateFlow<ArticleListState>(ArticleListState.Empty)

    private lateinit var currentPage: Page<Article>

    fun observeState() = uiState.asStateFlow()

    @Suppress("MemberVisibilityCanBePrivate")
    fun getArticles(section: NewsSection?) = launchCoroutineScope {
        uiState.value = ArticleListState.Loading
        repository.getArticlePage(section = section ?: defaultSection)
            .onSuccess { page ->
                currentPage = page
                uiState.value = ArticleListState.Content(page.items, page is Page.HasNextPage)
                page.items.forEach { println(it.title) }
            }
            .onError { println("ERROR - ${it.message}") }
    }
}