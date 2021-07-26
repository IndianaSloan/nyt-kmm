package com.ciaransloan.nytkmm.domain.remote.model

import com.ciaransloan.nytkmm.domain.remote.KEY_NUM_RESULTS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NytResponseApiModel<T>(
    val status: String,
    @SerialName(KEY_NUM_RESULTS)
    val objectCount: Int,
    val results: T
)