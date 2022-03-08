package com.chb.pecodetest.di

import android.content.Context
import com.chb.pecodetest.NotificationHelper
import dagger.Module
import dagger.Provides

@Module
class NotificationsModule {

    @Provides
    fun provideNotificationHelper(context: Context) : NotificationHelper {
        return NotificationHelper(context)
    }
}