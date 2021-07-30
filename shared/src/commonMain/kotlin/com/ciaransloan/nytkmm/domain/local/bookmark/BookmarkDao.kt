package com.ciaransloan.nytkmm.domain.local.bookmark

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.repository.model.Article
import kotlinx.datetime.LocalDate

internal class BookmarkDao(
    database: NytDatabase,
    private val mapper: BookmarkDaoMapper = BookmarkDaoMapper()
) : BookmarkDaoContract {

    private val queries = database.bookmarkQueries

    override fun insert(article: Article) {
        queries.insertBookmark(
            id = article.id,
            title = article.title,
            description = article.description,
            postedDate = 0L, //TODO
            webUrl = article.webUrl,
            thumbnailUrl = article.thumbnailUrl
        )
    }

    override fun insert(items: List<Article>) {
        items.forEach { insert(it) }
    }

    override fun getBookmarkIds(): List<String> {
        return queries.selectAll().executeAsList().map(mapper::mapToId)
    }

    override fun getArticlesBookmarked(): List<Article> {
        return queries.selectAll().executeAsList().map(mapper::map)
    }

    override fun remove(articleId: String) {
        queries.removeBookmark(articleId)
    }
}