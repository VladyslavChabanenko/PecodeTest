package com.chb.pecodetest.di

import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.chb.pecodetest.Constants.INITIAL_PAGE_COUNT
import com.chb.pecodetest.Constants.SHARED_PREFS_PAGE_COUNT_KEY
import com.chb.pecodetest.PecodeTestPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class PagerAdapterModule(
    private val fragmentActivity: FragmentActivity) {

    @Provides
    fun provideAdapter(sharedPrefs: SharedPreferences): PecodeTestPagerAdapter =
        PecodeTestPagerAdapter(fragmentActivity, sharedPrefs.getInt(SHARED_PREFS_PAGE_COUNT_KEY, INITIAL_PAGE_COUNT))
}