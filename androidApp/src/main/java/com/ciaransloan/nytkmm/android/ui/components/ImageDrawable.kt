package com.ciaransloan.nytkmm.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.ciaransloan.nytkmm.android.styles.ColorSecondary
import com.ciaransloan.nytkmm.android.styles.Dimens

@Composable
fun ImageDrawable(
    @DrawableRes resId: Int,
    tint: Color = ColorSecondary,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(resId),
        contentDescription = "",
        colorFilter = ColorFilter.tint(tint),
        modifier = Modifier
            .absolutePadding(left = Dimens.PaddingHalf, right = Dimens.PaddingHalf)
            .clickable { onClick() }
    )
}