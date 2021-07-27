package com.ciaransloan.nytkmm.domain.repository.model

/**
 * A domain model to represent an Article. Domain models handle null values from each data source
 * and prevents these from being exposed to Presentation layer.
 */
data class Article(
    val id: String,
    val title: String,
    val description: String,
    val postedDate: Long,
    val webUrl: String,
    val thumbnailUrl: String ? = null,
    val isFavorite: Boolean
)