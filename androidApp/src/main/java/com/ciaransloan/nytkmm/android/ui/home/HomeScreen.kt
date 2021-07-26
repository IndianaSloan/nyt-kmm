package com.ciaransloan.nytkmm.android.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.ui.article.ArticleListScreen
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val sections = listOf(
        NewsSection("all", "All"),
        NewsSection("travel", "Travel")
    )
    val pagerState = rememberPagerState(pageCount = sections.count())
    val coroutineScope = rememberCoroutineScope()
    val tabIndex = pagerState.currentPage
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            sections.forEachIndexed { index, section ->
                val icon = if (section.id == "all") R.drawable.ic_all else R.drawable.ic_travel
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = { Icon(painter = painterResource(id = icon), contentDescription = "") }
                )
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1F)) { index ->
            ArticleListScreen(sections[index])
        }
    }
}