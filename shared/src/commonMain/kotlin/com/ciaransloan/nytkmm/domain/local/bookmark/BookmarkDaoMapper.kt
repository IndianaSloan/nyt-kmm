package com.ciaransloan.nytkmm.domain.local.bookmark

import com.ciaransloan.nytkmm.domain.repository.model.Article
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class BookmarkDaoMapper {

    fun mapToId(bookmark: Bookmark): String {
        return bookmark.id
    }

    fun map(bookmark: Bookmark): Article {
        return Article(
            id = bookmark.id,
            title = bookmark.title,
            description = bookmark.description,
            postedDate = mapDate(bookmark.postedDate),
            webUrl = bookmark.webUrl,
            thumbnailUrl = bookmark.thumbnailUrl,
            isFavorite = true
        )
    }

    private fun mapDate(epochMillis: Long): LocalDate {
        return Instant.fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}