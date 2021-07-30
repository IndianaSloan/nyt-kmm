package com.ciaransloan.nytkmm.domain.local.bookmark

import com.ciaransloan.nytkmm.domain.repository.model.Article
import kotlinx.datetime.LocalDate

internal class BookmarkDaoMapper {

    fun mapToId(bookmark: Bookmark): String {
        return bookmark.id
    }

    fun map(bookmark: Bookmark): Article {
        return  Article(
            id = bookmark.id,
            title = bookmark.title,
            description = bookmark.description,
            postedDate = LocalDate(2021, 1, 1), //TODO
            webUrl = bookmark.webUrl,
            thumbnailUrl = bookmark.thumbnailUrl,
            isFavorite = true
        )
    }
}