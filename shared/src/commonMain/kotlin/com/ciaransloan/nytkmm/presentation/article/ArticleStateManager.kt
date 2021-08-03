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
import org.koin.core.parameter.parametersOf

class ArticleStateManager(database: NytDatabase) : BaseStateManager(), KoinComponent {

    private val repository by inject<NytRepositoryContract>(parameters = { parametersOf(database) })
    private val uiMapper by inject<ArticleUIMapper>()
    private val sectionUiMapper by inject<SectionUIMapper>()
    private val uiState = MutableStateFlow<ArticleListState>(ArticleListState.Empty)

    private val bookmarkDao: BookmarkDaoContract = BookmarkDao(database)

    private lateinit var currentPage: Page<Article>
    private val loadedItems = mutableListOf<Article>()

    fun observeState() = uiState.asStateFlow()

    fun getArticles(sectionUIModel: SectionUIModel?) = launchCoroutineScope {
        uiState.value = ArticleListState.Loading
        val section = sectionUiMapper.mapSectionUIModel(sectionUIModel)
        repository.getArticlePage(section = section)
            .onSuccess { page -> handlePageResult(page) }
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

    fun bookmarkArticle(articleId: String) {
        when (val articleIndex = loadedItems.indexOfFirst { it.id == articleId }) {
            NO_INDEX -> println("CANT FIND ARTICLE IN LOADED ITEMS")
            else -> {
                println("SAVING BOOKMARK")
                val updatedArticle = loadedItems[articleIndex].copy(isFavorite = true)
                bookmarkDao.insert(updatedArticle)
                replaceArticle(updatedArticle, articleIndex)
            }
        }
    }

    fun unBookmarkArticle(articleId: String) {
        when (val articleIndex = loadedItems.indexOfFirst { it.id == articleId }) {
            NO_INDEX -> println("CANT FIND ARTICLE IN LOADED ITEMS")
            else -> {
                println("REMOVING BOOKMARK")
                val updatedArticle = loadedItems[articleIndex].copy(isFavorite = false)
                bookmarkDao.remove(articleId)
                replaceArticle(updatedArticle, articleIndex)
            }
        }
    }

    fun getBookmarks() {
        val ids = bookmarkDao.getBookmarkIds()
        println("============================")
        println("GOT ${ids.count()} BOOKMARKS")
        println("============================")
    }

    private fun handlePageResult(page: Page<Article>) {
        currentPage = page
        loadedItems.addAll(page.items)
        uiState.value = uiMapper.map(loadedItems, page)
    }

    private fun replaceArticle(article: Article, index: Int) {
        with(loadedItems) {
            removeAt(index)
            add(index, article)
        }
        handlePageResult(currentPage)
    }
}

private const val NO_INDEX = -1