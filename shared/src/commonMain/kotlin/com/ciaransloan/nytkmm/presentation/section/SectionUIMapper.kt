package com.ciaransloan.nytkmm.presentation.section

import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.ciaransloan.nytkmm.domain.repository.model.defaultSection
import com.ciaransloan.nytkmm.presentation.section.model.SectionListState
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel

internal class SectionUIMapper {

    fun map(sections: List<NewsSection>): SectionListState {
        return when (sections.isEmpty()) {
            true -> SectionListState.Empty
            false -> {
                val uiModels = sections.map { section -> SectionUIModel(section.id, section.name) }
                SectionListState.Content(uiModels)
            }
        }
    }

    fun mapSectionUIModel(uiModel: SectionUIModel?): NewsSection {
        return uiModel?.let { section -> NewsSection(section.id, section.title) } ?: defaultSection
    }
}