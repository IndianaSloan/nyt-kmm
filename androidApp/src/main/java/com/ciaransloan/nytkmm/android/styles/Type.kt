package com.ciaransloan.nytkmm.android.styles

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ciaransloan.nytkmm.android.R

val Cormorand = FontFamily(
    Font(R.font.cormorant_garamond_regular),
    Font(R.font.cormorant_garamond_bold, FontWeight.Bold)
)
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Cormorand,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.TextSizeMed
    ),
    body1 = TextStyle(
        fontFamily = Cormorand,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.TextSizeMed
    ),
    caption = TextStyle(
        fontFamily = Cormorand,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.TextSizeSmall,
        color = ColorCaption
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.TextSizeXSmall,
        color = ColorSubtitle
    ),
)