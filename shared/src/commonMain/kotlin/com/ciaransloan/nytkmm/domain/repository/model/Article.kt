package com.ciaransloan.nytkmm.domain.repository.model

/**
 * A domain model to represent an Article. Domain models handle null values from each data source
 * and prevents these from being exposed to Presentation layer.
 */
sealed class Article(
    open val id: String,
    open val title: String,
    open val postedDate: Long,
    open val webUrl: String
) {
    data class WithImage(
        override val id: String,
        override val title: String,
        override val postedDate: Long,
        override val webUrl: String,
        val thumbnailUrl: String
    ) : Article(id, title, postedDate, webUrl)

    data class NoImage(
        override val id: String,
        override val title: String,
        override val postedDate: Long,
        override val webUrl: String
    ) : Article(id, title, postedDate, webUrl)

    fun getImageUrl(): String? = when (this) {
        is WithImage -> thumbnailUrl
        else -> null
    }
}