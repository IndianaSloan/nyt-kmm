package com.ciaransloan.nytkmm.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ciaransloan.nytkmm.android.styles.NytTheme
import com.ciaransloan.nytkmm.android.ui.home.HomeScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NytTheme {
                HomeScreen()
            }
        }
    }
}
