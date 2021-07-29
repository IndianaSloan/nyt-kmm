package com.ciaransloan.nytkmm

import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState
import com.ciaransloan.nytkmm.presentation.section.SectionListStateManager
import com.ciaransloan.nytkmm.presentation.section.model.SectionListState

fun ArticleStateManager.collectState(block: (ArticleListState) -> Unit) =
    observeState().wrap().watch(block)

fun SectionListStateManager.collectState(block : (SectionListState) -> Unit) =
    observeState().wrap().watch(block)