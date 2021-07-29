package com.ciaransloan.nytkmm.android.di

import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.section.SectionListStateManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideArticleStateManager(): ArticleStateManager = ArticleStateManager()

    @Provides
    fun provideSectionListStateManager(): SectionListStateManager = SectionListStateManager()

    @Provides
    fun provideGson() = GsonBuilder().create()
}