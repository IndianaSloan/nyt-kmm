package com.ciaransloan.nytkmm.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.styles.ColorBackground
import com.ciaransloan.nytkmm.android.styles.ColorPrimary

@Preview(showSystemUi = true, apiLevel = 23)
@Composable
fun NytToolbar(showBackArrow: Boolean = false, onBackPressed: () -> Unit = {}) {
    TopAppBar(
        backgroundColor = ColorBackground,
        modifier = Modifier.height(56.dp),
        elevation = 0.dp,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 46.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.img_logo), contentDescription = "")
            }
        },
        navigationIcon = {
            if (showBackArrow) ImageDrawable(R.drawable.ic_back, ColorPrimary) { onBackPressed() }
        }
    )
}