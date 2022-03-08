package com.chb.pecodetest.di

import com.chb.pecodetest.MainActivity
import com.chb.pecodetest.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [PagerAdapterModule::class, NotificationsModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}