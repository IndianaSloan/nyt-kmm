package com.ciaransloan.nytkmm.presentation.section.model

import com.ciaransloan.nytkmm.domain.repository.model.NewsSection

internal class SectionUIMapper {

    fun mapSection(section: SectionUIModel?): NewsSection {
        if (section == null) return NewsSection("all", "All")
        return NewsSection(id = section.id, name = section.title)
    }
}