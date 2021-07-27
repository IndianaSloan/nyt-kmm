package com.ciaransloan.nytkmm.android.app

import android.app.Application
import com.ciaransloan.nytkmm.di.initKoin
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

@HiltAndroidApp
class NytApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@NytApplication)
            androidLogger()
        }
    }
}