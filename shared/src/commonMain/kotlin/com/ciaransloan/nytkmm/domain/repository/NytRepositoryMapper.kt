package com.ciaransloan.nytkmm.domain.repository

import com.ciaransloan.nytkmm.domain.remote.model.ArticleApiModel
import com.ciaransloan.nytkmm.domain.remote.model.SectionApiModel
import com.ciaransloan.nytkmm.domain.repository.base.BaseMapper
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import kotlinx.datetime.*

internal class NytRepositoryMapper : BaseMapper() {

    fun mapSections(items: List<SectionApiModel>): List<NewsSection> {
        return items.mapNotNull {
            NewsSection(
                id = it.sectionKey ?: return@mapNotNull null,
                name = it.sectionName ?: return@mapNotNull null
            )
        }
    }

    fun mapArticles(items: List<ArticleApiModel>, bookmarkIds: List<String>): List<Article> {
        return items.mapNotNull { apiModel ->
            val imageUrl =
                apiModel.multimedia?.firstOrNull { it.format == "Normal" && it.type == "image" }?.url
            Article(
                id = apiModel.id ?: return@mapNotNull null,
                title = apiModel.title ?: return@mapNotNull null,
                description = apiModel.abstract ?: "",
                postedDate = apiModel.publishedDate?.let { mapDate(it) }
                    ?: return@mapNotNull null,
                thumbnailUrl = imageUrl ?: apiModel.thumbnailUrl,
                webUrl = apiModel.url ?: return@mapNotNull null,
                isFavorite = bookmarkIds.contains(apiModel.id)
            )
        }
    }

    private fun mapDate(isoOffsetDateTime: String): LocalDate {
        val dateArray = isoOffsetDateTime.take(10).split("-")
        val year = dateArray[0].toInt()
        val month = dateArray[1].toInt()
        val day = dateArray[2].toInt()
        return LocalDate(year, month, day)
    }
}