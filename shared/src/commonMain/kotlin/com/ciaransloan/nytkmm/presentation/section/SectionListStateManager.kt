package com.ciaransloan.nytkmm.presentation.section

import com.ciaransloan.nytkmm.domain.repository.NytRepositoryContract
import com.ciaransloan.nytkmm.domain.repository.base.onError
import com.ciaransloan.nytkmm.domain.repository.base.onSuccess
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState
import com.ciaransloan.nytkmm.presentation.base.BaseStateManager
import com.ciaransloan.nytkmm.presentation.section.model.SectionListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SectionListStateManager : BaseStateManager(), KoinComponent {

    private val repository by inject<NytRepositoryContract>()
    private val mapper by inject<SectionUIMapper>()
    private val uiState = MutableStateFlow<SectionListState>(SectionListState.Empty)

    fun observeState() = uiState.asStateFlow()

    fun getSections() = launchCoroutineScope {
        uiState.value = SectionListState.Loading
        repository.getSections()
            .onSuccess { sections -> handleResult(sections) }
            .onError { error -> println("ERROR: ${error.message}") }
    }

    private fun handleResult(sections: List<NewsSection>) {
        uiState.value = mapper.map(sections)
    }
}