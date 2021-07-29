package com.ciaransloan.nytkmm.android.ui.section

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ciaransloan.nytkmm.android.styles.Dimens
import com.ciaransloan.nytkmm.android.styles.Typography
import com.ciaransloan.nytkmm.presentation.section.model.SectionListState
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel

@Composable
fun SectionListScreen() {
    val viewModel: SectionListViewModel = hiltViewModel()
    DisposableEffect(key1 = viewModel) {
        viewModel.getSections()
        onDispose { }
    }

    val uiState = viewModel.uiState.collectAsState()
    when (val model = uiState.value) {
        is SectionListState.Content -> {
            LazyColumn {
                items(model.sections) { uiItem ->
                    SectionListItem(section = uiItem)
                }
            }
        }
    }
}

@Composable
fun SectionListItem(section: SectionUIModel) {
    Text(
        text = section.title,
        modifier = Modifier.padding(Dimens.PaddingDefault),
        style = Typography.h1.copy(fontSize = 18.sp)
    )
}