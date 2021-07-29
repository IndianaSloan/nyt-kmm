package com.ciaransloan.nytkmm.domain.local

import android.content.Context
import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NytDatabase.Schema, context, "nyt.db")
    }
}