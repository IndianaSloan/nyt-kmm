package com.ciaransloan.nytkmm.di

import com.ciaransloan.nytkmm.datasource.cache.NytDatabase
import com.ciaransloan.nytkmm.domain.local.DatabaseFactory
import com.ciaransloan.nytkmm.domain.local.DriverFactory

object PersistenceModule {
    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    val database: NytDatabase by lazy { DatabaseFactory(driverFactory).createDatabase() }
}