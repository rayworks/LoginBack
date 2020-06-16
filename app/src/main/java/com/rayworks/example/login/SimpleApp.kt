package com.rayworks.example.login

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber

class SimpleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        enableTracking()

        Timber.plant(Timber.DebugTree())
    }


    private fun enableTracking() {
        AppCenter.start(this, "f7c424c7-0b8e-4bc3-bc7b-0531fc52f2b3",
            Analytics::class.java, Crashes::class.java
        )
    }
}