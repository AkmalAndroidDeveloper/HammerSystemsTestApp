package com.example.androidtestapplication.app

import android.app.Application
import com.example.androidtestapplication.di.appModule
import com.example.androidtestapplication.di.dataModule
import com.example.androidtestapplication.di.domainModule
import com.example.androidtestapplication.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule, networkModule))
        }
    }
}