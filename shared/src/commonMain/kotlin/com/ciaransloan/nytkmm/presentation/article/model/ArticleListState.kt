package com.ciaransloan.nytkmm.presentation.article.model

sealed class ArticleListState {
    object Loading : ArticleListState()
    class Content(val items: List<ArticleUIModel>, val hasNextPage: Boolean) : ArticleListState()
    object Empty : ArticleListState()
}

data class ArticleUIModel(
    val id: String,
    val title: String,
    val description: String,
    val postedDate: String,
    val webUrl: String,
    val thumbnailUrl: String?,
    val isFavorite: Boolean
)