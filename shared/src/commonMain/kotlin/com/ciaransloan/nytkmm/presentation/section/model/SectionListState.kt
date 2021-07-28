package com.ciaransloan.nytkmm.presentation.section.model

sealed class SectionListState {
    object Loading : SectionListState()
    class Content(val sections: List<SectionUIModel>) : SectionListState()
    object Empty : SectionListState()
}

data class SectionUIModel(
    val id: String,
    val title: String
)