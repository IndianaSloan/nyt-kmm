package com.ciaransloan.nytkmm.domain.repository

import com.ciaransloan.nytkmm.domain.remote.NytApi
import com.ciaransloan.nytkmm.domain.remote.PAGE_SIZE
import com.ciaransloan.nytkmm.domain.repository.base.PagingRepositoryHelper
import com.ciaransloan.nytkmm.domain.repository.base.RepositoryResult
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection

internal class NytRepository(
    private val api: NytApi,
    private val mapper: NytRepositoryMapper,
    private val pagingRepositoryHelper: PagingRepositoryHelper
) : NytRepositoryContract {

    override suspend fun getArticlePage(
        page: Int,
        section: NewsSection
    ): RepositoryResult<Page<Article>> {
        return pagingRepositoryHelper.callWithPaging(
            page = page,
            modelProvider = { offset -> api.getArticles(section.id, offset) },
            modelMapper = mapper::mapArticles,
            pageSize = PAGE_SIZE
        )
    }

    override suspend fun getSections(): RepositoryResult<List<NewsSection>> {
        return mapper.mapApiResult(
            apiCall = api.getSections(),
            callName = "[GET] /sections.json",
            modelMapper = { mapper.mapSections(it.results) }
        )
    }
}