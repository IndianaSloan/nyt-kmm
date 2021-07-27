package com.ciaransloan.nytkmm.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.ciaransloan.nytkmm.android.styles.ColorBackground
import com.ciaransloan.nytkmm.android.styles.ColorSurface
import com.ciaransloan.nytkmm.android.styles.NytTheme
import com.ciaransloan.nytkmm.android.ui.navigation.Navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                with(uiController) {
                    setStatusBarColor(color = ColorBackground, darkIcons = useDarkIcons)
                    setNavigationBarColor(color = Color.Black)
                }
            }
            NytTheme {
                Navigation(gson)
            }
        }
    }
}
