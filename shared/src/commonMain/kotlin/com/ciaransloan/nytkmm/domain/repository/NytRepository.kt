package com.ciaransloan.nytkmm.domain.repository

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDao
import com.ciaransloan.nytkmm.domain.local.bookmark.BookmarkDaoContract
import com.ciaransloan.nytkmm.domain.remote.NytApi
import com.ciaransloan.nytkmm.domain.remote.PAGE_SIZE
import com.ciaransloan.nytkmm.domain.repository.base.PagingRepositoryHelper
import com.ciaransloan.nytkmm.domain.repository.base.RepositoryError
import com.ciaransloan.nytkmm.domain.repository.base.RepositoryResult
import com.ciaransloan.nytkmm.domain.repository.base.flatMapSuspend
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection

internal class NytRepository(
    private val api: NytApi,
    private val mapper: NytRepositoryMapper,
    private val pagingRepositoryHelper: PagingRepositoryHelper,
    private val database: NytDatabase
) : NytRepositoryContract {

    private val bookmarkDao: BookmarkDaoContract = BookmarkDao(database)

    override suspend fun getArticlePage(
        page: Int,
        section: NewsSection
    ): RepositoryResult<Page<Article>> {
        return getBookmarkIds().flatMapSuspend { bookmarkIds ->
            pagingRepositoryHelper.callWithPaging(
                page = page,
                modelProvider = { offset -> api.getArticles(section.id, offset) },
                modelMapper = { articles -> mapper.mapArticles(articles, bookmarkIds) },
                pageSize = PAGE_SIZE
            )
        }
    }

    override suspend fun getSections(): RepositoryResult<List<NewsSection>> {
        return mapper.mapApiResult(
            apiCall = api.getSections(),
            callName = "[GET] /sections.json",
            modelMapper = { mapper.mapSections(it.results) }
        )
    }

    override suspend fun getBookmarkIds(): RepositoryResult<List<String>> {
        return try {
            val bookmarkIds = bookmarkDao.getBookmarkIds()
            RepositoryResult.success(bookmarkIds)
        } catch (e: Exception) {
            RepositoryResult.error(RepositoryError.fromException(e))
        }
    }
}