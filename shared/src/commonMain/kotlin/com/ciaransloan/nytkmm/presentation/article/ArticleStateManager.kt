package com.ciaransloan.nytkmm.presentation.article

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDao
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDaoContract
import com.ciaransloan.nytkmm.domain.repository.NytRepositoryContract
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import com.ciaransloan.nytkmm.domain.repository.base.onError
import com.ciaransloan.nytkmm.domain.repository.base.onSuccess
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState
import com.ciaransloan.nytkmm.presentation.base.BaseStateManager
import com.ciaransloan.nytkmm.presentation.section.SectionUIMapper
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ArticleStateManager(database: NytDatabase) : BaseStateManager(), KoinComponent {

    private val repository by inject<NytRepositoryContract>()
    private val uiMapper by inject<ArticleUIMapper>()
    private val sectionUiMapper by inject<SectionUIMapper>()
    private val uiState = MutableStateFlow<ArticleListState>(ArticleListState.Empty)

    private val bookmarkDao: BookmarkDaoContract = BookmarkDao(database)

    private lateinit var currentPage: Page<Article>
    private val loadedPages = mutableListOf<Page<Article>>()

    fun observeState() = uiState.asStateFlow()

    fun getArticles(sectionUIModel: SectionUIModel?) = launchCoroutineScope {
        uiState.value = ArticleListState.Loading
        val section = sectionUiMapper.mapSectionUIModel(sectionUIModel)
        repository.getArticlePage(section = section)
            .onSuccess { page ->
                handlePageResult(page)
            }
            .onError { error -> println("ERROR - ${error.message}") }
    }

    fun loadNextPage() = launchCoroutineScope {
        when (val currentPage = currentPage) {
            is Page.HasNextPage -> {
                currentPage.nextPage()
                    .onSuccess { page -> handlePageResult(page) }
                    .onError { error -> println("ERROR - ${error.message}") }
            }
        }
    }

    fun save(articleId: String) {
        loadedPages.flatMap { it.items }.find { it.id == articleId }?.let {
            println("SAVING BOOKMARK")
            bookmarkDao.insert(it)
        }
    }

    fun remove(articleId: String) {
        bookmarkDao.remove(articleId)
    }

    fun getBookmarks() {
        val ids = bookmarkDao.getBookmarkIds()
        println("============================")
        println("GOT ${ids.count()} BOOKMARKS")
        println("============================")
    }

    private fun handlePageResult(page: Page<Article>) {
        currentPage = page
        loadedPages.add(page)
        val articles = loadedPages.flatMap { it.items }
        uiState.value = uiMapper.map(articles, page)
    }
}