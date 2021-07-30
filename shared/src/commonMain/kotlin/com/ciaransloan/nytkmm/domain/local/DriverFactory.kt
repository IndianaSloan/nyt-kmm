package com.ciaransloan.nytkmm.domain.local

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

class DatabaseFactory(private val driverFactory: DriverFactory) {
    fun createDatabase(): NytDatabase {
        return NytDatabase.invoke(driverFactory.createDriver())
    }
}