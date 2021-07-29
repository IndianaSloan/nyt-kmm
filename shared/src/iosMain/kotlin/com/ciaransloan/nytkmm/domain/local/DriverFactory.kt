package com.ciaransloan.nytkmm.domain.local

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NytDatabase.Schema, "nyt.db")
    }
}