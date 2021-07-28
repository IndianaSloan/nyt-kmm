package com.ciaransloan.nytkmm.domain.repository.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

/**
 * A domain model to represent an Article. Domain models handle null values from each data source
 * and prevents these from being exposed to Presentation layer.
 */
internal data class Article(
    val id: String,
    val title: String,
    val description: String,
    val postedDate: LocalDate,
    val webUrl: String,
    val thumbnailUrl: String?,
    val isFavorite: Boolean
)