package com.ciaransloan.nytkmm.presentation.article.model

import com.ciaransloan.nytkmm.domain.repository.model.Article

sealed class ArticleListState {
    object Loading : ArticleListState()
    class Content(val items: List<Article>, val hasNextPage: Boolean) : ArticleListState()
    object Empty : ArticleListState()
}