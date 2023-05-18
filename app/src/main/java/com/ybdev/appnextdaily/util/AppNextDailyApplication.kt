package com.ybdev.appnextdaily.util

import android.app.Application
import com.ybdev.appnextdaily.di.appModule
import com.ybdev.appnextdaily.repository.room_db.DailyRoomDB
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AppNextDailyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppNextDailyApplication)
            modules(appModule)
        }
    }
}