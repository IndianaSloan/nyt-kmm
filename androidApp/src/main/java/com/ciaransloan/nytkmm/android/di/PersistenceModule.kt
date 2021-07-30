package com.ciaransloan.nytkmm.android.di

import android.content.Context
import com.ciaransloan.nytkmm.android.app.NytApplication
import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.local.DatabaseFactory
import com.ciaransloan.nytkmm.domain.local.DriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(context: NytApplication): NytDatabase =
        DatabaseFactory(DriverFactory(context)).createDatabase()
}