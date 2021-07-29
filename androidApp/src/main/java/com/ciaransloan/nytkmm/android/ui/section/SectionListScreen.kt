package com.ciaransloan.nytkmm.android.ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.styles.ColorPrimary
import com.ciaransloan.nytkmm.android.styles.Dimens
import com.ciaransloan.nytkmm.android.styles.Typography
import com.ciaransloan.nytkmm.android.ui.components.ImageDrawable
import com.ciaransloan.nytkmm.android.ui.components.ProgressMask
import com.ciaransloan.nytkmm.presentation.section.model.SectionListState
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel

@Composable
fun SectionListScreen(onSectionTapped: (SectionUIModel) -> Unit) {
    val viewModel: SectionListViewModel = hiltViewModel()
    DisposableEffect(key1 = viewModel) {
        viewModel.getSections()
        onDispose { /* do nothing */ }
    }

    val uiState = viewModel.uiState.collectAsState()
    val data = when (val model = uiState.value) {
        is SectionListState.Content -> model.sections
        is SectionListState.Loading -> {
            ProgressMask()
            emptyList()
        }
        else -> emptyList()
    }
    LazyColumn {
        items(data) { uiItem ->
            SectionListItem(section = uiItem) { section -> onSectionTapped(section) }
        }
    }
}

@Composable
fun SectionListItem(section: SectionUIModel, onClick: (SectionUIModel) -> Unit) {
    Row(
        modifier = ListItemInsets.clickable { onClick(section) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = section.title,
            modifier = Modifier
                .padding(Dimens.PaddingDefault)
                .fillMaxWidth(0.9F),
            style = Typography.h1.copy(fontSize = 18.sp)
        )

        ImageDrawable(resId = R.drawable.ic_caret_right, tint = ColorPrimary)
    }
}

private val ListItemInsets = Modifier
    .height(Dimens.SectionItemHeight)
    .fillMaxWidth()