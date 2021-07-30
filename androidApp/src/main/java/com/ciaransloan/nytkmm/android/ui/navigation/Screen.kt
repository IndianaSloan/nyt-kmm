package com.ciaransloan.nytkmm.android.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Screen(val route: String, val args: List<NamedNavArgument>) {
    object ArticleList : Screen("articleList", emptyList())
    object ArticleListSection : Screen(
        "articleList/{$ARGS_SECTION}",
        listOf(navArgument(ARGS_SECTION) { type = NavType.StringType })
    )
    object SectionList : Screen("sectionList", emptyList())
    object Bookmarks : Screen("bookmarks", emptyList())
}

private const val ARGS_SECTION = "section"