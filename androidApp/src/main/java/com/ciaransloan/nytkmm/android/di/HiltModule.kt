package com.ciaransloan.nytkmm.android.di

import android.content.Context
import com.ciaransloan.nytkmm.android.app.NytApplication
import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.presentation.article.ArticleStateManager
import com.ciaransloan.nytkmm.presentation.section.SectionListStateManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): NytApplication {
        return context as NytApplication
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideArticleStateManager(database: NytDatabase): ArticleStateManager =
        ArticleStateManager(database)

    @Provides
    fun provideSectionListStateManager(): SectionListStateManager = SectionListStateManager()
}