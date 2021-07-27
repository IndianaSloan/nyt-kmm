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
        return items.mapNotNull { apiModel ->
            val imageUrl =
                apiModel.multimedia?.firstOrNull { it.format == "Normal" && it.type == "image" }?.url
            Article(
                id = apiModel.id ?: return@mapNotNull null,
                title = apiModel.title ?: return@mapNotNull null,
                description = apiModel.abstract ?: "",
                postedDate = 0L,
                thumbnailUrl = imageUrl ?: apiModel.thumbnailUrl,
                webUrl = apiModel.url ?: return@mapNotNull null,
                isFavorite = false
            )
        }
    }
}