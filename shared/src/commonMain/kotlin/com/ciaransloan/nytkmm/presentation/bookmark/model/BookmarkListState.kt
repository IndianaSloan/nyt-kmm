package com.ciaransloan.nytkmm.presentation.bookmark.model

sealed class BookmarkListState {
    object Loading : BookmarkListState()
    class Content(val items: List<BookmarkUIModel>) : BookmarkListState()
    object Empty : BookmarkListState()
}

data class BookmarkUIModel(
    val id: String,
    val title: String,
    val description: String,
    val postedDate: String,
    val webUrl: String,
    val thumbnailUrl: String?
)