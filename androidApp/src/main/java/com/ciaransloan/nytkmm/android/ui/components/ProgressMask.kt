package com.ciaransloan.nytkmm.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressMask() {
    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
}