package com.ciaransloan.nytkmm.di

import com.ciaransloan.nytkmm.domain.ApiClient
import com.ciaransloan.nytkmm.domain.remote.NytApi
import com.ciaransloan.nytkmm.domain.repository.NytRepository
import com.ciaransloan.nytkmm.domain.repository.NytRepositoryContract
import com.ciaransloan.nytkmm.domain.repository.NytRepositoryMapper
import com.ciaransloan.nytkmm.domain.repository.base.PagingRepositoryHelper
import com.ciaransloan.nytkmm.presentation.article.ArticleUIMapper
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIMapper
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(listOf(apiModule, presentationModule, repositoryModule))
}

// Used to implement from iOS application
fun initKoin() = initKoin {}

private val repositoryModule = module {
    single<NytRepositoryContract> { NytRepository(get(), get(), get()) }

    factory { PagingRepositoryHelper() }
    factory { NytRepositoryMapper() }
}

private val apiModule = module {
    single { NytApi(ApiClient.build()) }
}

private val presentationModule = module {
    factory { ArticleUIMapper() }
    factory { SectionUIMapper() }
}