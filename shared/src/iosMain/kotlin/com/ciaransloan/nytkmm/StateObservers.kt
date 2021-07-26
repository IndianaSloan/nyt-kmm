package com.ciaransloan.nytkmm

import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState

fun ArticleStateManager.collectState(block: (ArticleListState) -> Unit) =
    observeState().wrap().watch(block)