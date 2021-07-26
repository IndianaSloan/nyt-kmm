package com.ciaransloan.nytkmm.domain.repository

import com.ciaransloan.nytkmm.domain.remote.model.ArticleApiModel
import com.ciaransloan.nytkmm.domain.remote.model.SectionApiModel
import com.ciaransloan.nytkmm.domain.repository.base.BaseMapper
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection

internal class NytRepositoryMapper : BaseMapper() {

    fun mapSections(items: List<SectionApiModel>): List<NewsSection> {
        return items.mapNotNull {
            NewsSection(
                id = it.sectionKey ?: return@mapNotNull null,
                name = it.sectionName ?: return@mapNotNull null
            )
        }
    }

    fun mapArticles(items: List<ArticleApiModel>): List<Article> {
        return items.mapNotNull {
            when (it.thumbnailUrl.isNullOrEmpty()) {
                true -> Article.NoImage(
                    id = it.id ?: return@mapNotNull null,
                    title = it.title ?: return@mapNotNull null,
                    postedDate = 0L,
                    webUrl = it.url ?: return@mapNotNull null
                )
                false -> Article.WithImage(
                    id = it.id ?: return@mapNotNull null,
                    title = it.title ?: return@mapNotNull null,
                    postedDate = 0L,
                    thumbnailUrl = it.thumbnailUrl,
                    webUrl = it.url ?: return@mapNotNull null
                )
            }
        }
    }
}