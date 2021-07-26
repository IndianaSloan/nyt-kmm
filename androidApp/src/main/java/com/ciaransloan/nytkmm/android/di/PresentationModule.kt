package com.ciaransloan.nytkmm.android.di

import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import org.koin.dsl.module

val presentationModule = module {
    single { ArticleStateManager() }
}