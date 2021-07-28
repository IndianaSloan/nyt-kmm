package com.ciaransloan.nytkmm.domain.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class to represent an Article as returned from the API.
 *
 * TODO: Its not clear from the documentation (https://developer.nytimes.com/docs/timeswire-product/1/routes/content/%7Bsource%7D/%7Bsection%7D.json/get)
 * but using slug_name as an assumed UUID for each article. This needs to be verified
 * before being pushed to production.
 */
@Serializable
internal data class ArticleApiModel(
    @SerialName("slug_name")
    val id: String? = null,
    val title: String? = null,
    val abstract: String? = null,
    @SerialName("published_date")
    val publishedDate: String? = null,
    @SerialName("thumbnail_standard")
    val thumbnailUrl: String? = null,
    val url: String? = null,
    val multimedia: List<ArticleMediaApiModel>? = null
)

@Serializable
internal data class ArticleMediaApiModel(
    val url: String? = null,
    val format: String? = null,
    val type: String? = null
)