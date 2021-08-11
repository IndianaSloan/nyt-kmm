package com.ciaransloan.nytkmm.presentation.bookmark

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDao
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDaoContract
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.presentation.base.BaseStateManager
import com.ciaransloan.nytkmm.presentation.bookmark.model.BookmarkListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookmarkStateManager(database: NytDatabase) : BaseStateManager(), KoinComponent {

    private val uiMapper by inject<BookmarkUIMapper>()
    private val uiState = MutableStateFlow<BookmarkListState>(BookmarkListState.Empty)

    private val bookmarkDao: BookmarkDaoContract = BookmarkDao(database)

    private val loadedItems = mutableListOf<Article>()

    fun observeState() = uiState.asStateFlow()

    fun getBookmarks() = launchCoroutineScope {
        loadedItems.clear()
        handleResult(bookmarkDao.getArticlesBookmarked())
    }

    fun unBookmarkArticle(articleId: String) {
        when (loadedItems.indexOfFirst { it.id == articleId }) {
            NO_INDEX -> println("CANT FIND ARTICLE IN LOADED ITEMS")
            else -> {
                bookmarkDao.remove(articleId)
                getBookmarks()
            }
        }
    }

    private fun handleResult(data: List<Article>) {
        loadedItems.addAll(data)
        uiState.value = BookmarkListState.Content(uiMapper.map(loadedItems))
    }
}

private const val NO_INDEX = -1