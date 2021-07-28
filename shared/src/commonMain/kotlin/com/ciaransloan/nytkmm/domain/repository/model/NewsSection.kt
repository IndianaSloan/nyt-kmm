package com.ciaransloan.nytkmm.domain.repository.model

internal data class NewsSection(val id: String, val name: String)

internal val defaultSection = NewsSection("all", "All")