package com.app.weatherguard.app

import android.app.Application
import com.app.weatherguard.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class WeatherGuard : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        plantDebugger()
    }

    private fun plantDebugger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherGuard)
            modules(
                myModules
            )
        }

    }
}
