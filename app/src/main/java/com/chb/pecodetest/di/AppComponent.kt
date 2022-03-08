package com.chb.pecodetest.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [SharedPrefsModule::class])
interface AppComponent {

    fun inject(application: Application)

    fun getActivityComponent(pagerAdapterModule: PagerAdapterModule): ActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}