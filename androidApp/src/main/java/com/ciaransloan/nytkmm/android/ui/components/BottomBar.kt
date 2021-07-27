package com.ciaransloan.nytkmm.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ciaransloan.nytkmm.android.styles.ColorPrimary
import com.ciaransloan.nytkmm.android.styles.ColorSecondary

data class BottomBarItem(
    val title: String,
    @DrawableRes val icon: Int,
    val screenRoute: String
)

@Composable
fun BottomBar(items: List<BottomBarItem>, onClick: (Int) -> Unit = {}) {
    val selectedIndex = remember { mutableStateOf(0) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        backgroundColor = Color.White,
        elevation = 16.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items.mapIndexed { index, item ->
                BottomBarIcon(
                    index = index,
                    bottomBarItem = item,
                    isSelected = selectedIndex.value == index,
                    modifier = Modifier.weight(1F),
                    onClick = { clickedIndex ->
                        selectedIndex.value = clickedIndex
                        onClick(clickedIndex)
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBarIcon(
    index: Int,
    bottomBarItem: BottomBarItem,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    Image(
        painter = painterResource(id = bottomBarItem.icon),
        contentDescription = "",
        colorFilter = ColorFilter.tint(if (isSelected) ColorPrimary else ColorSecondary),
        modifier = modifier.clickable { onClick(index) }
    )
}