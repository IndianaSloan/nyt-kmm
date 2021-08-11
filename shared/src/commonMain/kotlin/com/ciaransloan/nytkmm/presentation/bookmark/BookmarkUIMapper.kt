package com.ciaransloan.nytkmm.presentation.bookmark

import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.presentation.bookmark.model.BookmarkUIModel
import kotlinx.datetime.LocalDate

internal class BookmarkUIMapper {

    fun map(articles: List<Article>): List<BookmarkUIModel> {
        return articles.map { article ->
            BookmarkUIModel(
                id = article.id,
                title = article.title,
                description = article.description,
                postedDate = mapDate(article.postedDate),
                webUrl = article.webUrl,
                thumbnailUrl = article.thumbnailUrl
            )
        }
    }

    private fun mapDate(date: LocalDate): String = "${date.month.name} ${date.dayOfMonth}"
}