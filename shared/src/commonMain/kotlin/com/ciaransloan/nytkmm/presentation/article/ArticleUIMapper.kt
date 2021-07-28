package com.ciaransloan.nytkmm.presentation.article

import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.presentation.article.model.ArticleUIModel
import kotlinx.datetime.LocalDate

internal class ArticleUIMapper {

    fun map(articles: List<Article>): List<ArticleUIModel> {
        return articles.map { article ->
            ArticleUIModel(
                id = article.id,
                title = article.title,
                description = article.description,
                postedDate = mapDate(article.postedDate),
                webUrl = article.webUrl,
                thumbnailUrl = article.thumbnailUrl,
                isFavorite = article.isFavorite
            )
        }
    }

    private fun mapDate(date: LocalDate): String = "${date.month.name} ${date.dayOfMonth}"
}