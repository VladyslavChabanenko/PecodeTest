package com.chb.pecodetest

import android.app.Application
import android.content.SharedPreferences
import com.chb.pecodetest.di.DaggerAppComponent
import javax.inject.Inject

class PecodeTestApp: Application() {

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(applicationContext).inject(this)
    }
}