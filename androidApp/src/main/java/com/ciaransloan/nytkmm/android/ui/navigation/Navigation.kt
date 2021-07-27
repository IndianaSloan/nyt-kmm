package com.ciaransloan.nytkmm.android.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.ui.article.ArticleListScreen
import com.ciaransloan.nytkmm.android.ui.components.BottomBar
import com.ciaransloan.nytkmm.android.ui.components.BottomBarItem
import com.ciaransloan.nytkmm.android.ui.components.NytToolbar
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.google.gson.Gson

@Composable
fun Navigation(gson: Gson) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { NytToolbar() },
        bottomBar = { BottomBar(bottomBarItems) { i -> navController.navigate(bottomBarItems[i].screenRoute) } },
        content = {
            NavHost(navController = navController, startDestination = Screen.ArticleList.route) {
                composable(route = Screen.ArticleList.route) { ArticleListScreen() }
                composable(
                    route = Screen.ArticleListSection.route,
                    Screen.ArticleListSection.args
                ) { backStackEntry ->
                    val section = backStackEntry.arguments?.getString("section")?.let { json ->
                        gson.fromJson(json, NewsSection::class.java)
                    }
                    ArticleListScreen(section)
                }
            }
        }
    )
}

val bottomBarItems = listOf(
    BottomBarItem(title = "Top Stories", icon = R.drawable.ic_all, Screen.ArticleList.route),
    BottomBarItem(title = "Bookmarks", icon = R.drawable.ic_bookmark, Screen.ArticleList.route),
    BottomBarItem(title = "More", icon = R.drawable.ic_menu, Screen.ArticleList.route)
)