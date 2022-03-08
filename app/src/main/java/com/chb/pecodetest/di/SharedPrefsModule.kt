package com.chb.pecodetest.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

private const val SHARED_PREFS_NAME = "SharedPrefs"

@Module
class SharedPrefsModule {

    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }
}
