package com.rayworks.example.login

import android.app.Application
import timber.log.Timber

class SimpleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}