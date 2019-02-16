package com.lndmflngs.colorizer

import android.app.Application
import com.lndmflngs.colorizer.extensions.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}