package com.gemslight.wingsstore

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WingsStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            Log.e(
                "Global error",
                throwable.message, throwable.cause
            )
        }
    }
}