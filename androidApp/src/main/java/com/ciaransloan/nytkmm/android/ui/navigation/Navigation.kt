package com.ciaransloan.nytkmm.android.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.ui.article.ArticleListScreen
import com.ciaransloan.nytkmm.android.ui.components.BottomBar
import com.ciaransloan.nytkmm.android.ui.components.BottomBarItem
import com.ciaransloan.nytkmm.android.ui.components.NytToolbar
import com.ciaransloan.nytkmm.android.ui.section.SectionListScreen
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel
import com.google.gson.Gson

@Composable
fun Navigation(gson: Gson) {
    val navController = rememberNavController()
    val showBackArrow = remember { mutableStateOf(false) }
    val showBottomBar = remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            NytToolbar(showBackArrow.value) { navController.navigateUp() }
        },
        bottomBar = {
            BottomBar(BottomBarItems) { i -> navController.navigate(BottomBarItems[i].screenRoute) }
        },
        content = {
            NavHost(navController = navController, startDestination = Screen.ArticleList.route) {
                composable(route = Screen.ArticleList.route) {
                    showBackArrow.value = false
                    ArticleListScreen()
                }
                composable(
                    route = Screen.ArticleListSection.route,
                    Screen.ArticleListSection.args
                ) { backStackEntry ->
                    showBackArrow.value = true
                    val section = backStackEntry.arguments?.getString("section")?.let { json ->
                        gson.fromJson(json, SectionUIModel::class.java)
                    }
                    ArticleListScreen(section)
                }
                composable(Screen.SectionList.route) {
                    showBackArrow.value = false
                    SectionListScreen { sectionUIModel ->
                        val args = gson.toJson(sectionUIModel)
                        navController.navigate("articleList/$args")
                    }
                }
            }
        }
    )
}

val BottomBarItems = listOf(
    BottomBarItem(title = "Top Stories", icon = R.drawable.ic_all, Screen.ArticleList.route),
    BottomBarItem(title = "Bookmarks", icon = R.drawable.ic_bookmark, Screen.ArticleList.route),
    BottomBarItem(title = "More", icon = R.drawable.ic_menu, Screen.SectionList.route)
)