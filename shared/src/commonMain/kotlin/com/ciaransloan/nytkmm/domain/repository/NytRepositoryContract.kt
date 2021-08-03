package com.ciaransloan.nytkmm.domain.repository

import com.ciaransloan.nytkmm.domain.repository.base.RepositoryResult
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection

internal interface NytRepositoryContract {

    suspend fun getSections(): RepositoryResult<List<NewsSection>>

    suspend fun getArticlePage(
        page: Int = 1,
        section: NewsSection
    ): RepositoryResult<Page<Article>>

    suspend fun getBookmarkIds(): RepositoryResult<List<String>>
}