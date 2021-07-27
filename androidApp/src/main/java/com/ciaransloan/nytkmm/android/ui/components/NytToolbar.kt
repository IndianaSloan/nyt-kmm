package com.ciaransloan.nytkmm.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.styles.ColorBackground
import com.ciaransloan.nytkmm.android.styles.ColorPrimary

@Preview(apiLevel = 23, showSystemUi = true)
@Composable
fun NytToolbar() {
    TopAppBar(
        backgroundColor = ColorBackground,
        modifier = Modifier.height(56.dp),
        elevation = 0.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ImageDrawable(resId = R.drawable.img_logo, tint = ColorPrimary) {}
        }
    }
}