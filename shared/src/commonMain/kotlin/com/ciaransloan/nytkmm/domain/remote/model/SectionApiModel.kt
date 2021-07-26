package com.ciaransloan.nytkmm.domain.remote.model

import com.ciaransloan.nytkmm.domain.remote.KEY_DISPLAY_NAME
import com.ciaransloan.nytkmm.domain.remote.KEY_SECTION
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionApiModel(
    @SerialName(KEY_SECTION)
    val sectionKey: String? = null,
    @SerialName(KEY_DISPLAY_NAME)
    val sectionName: String? = null
)
