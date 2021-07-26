package com.ciaransloan.nytkmm.domain.repository.model

data class NewsSection(val id: String, val name: String)

val defaultSection = NewsSection("all", "All")