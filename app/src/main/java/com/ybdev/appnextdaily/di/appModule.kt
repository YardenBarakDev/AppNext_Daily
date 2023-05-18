package com.ybdev.appnextdaily.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ybdev.appnextdaily.SharedViewModel
import com.ybdev.appnextdaily.repository.remote.DailyDataApi
import com.ybdev.appnextdaily.repository.remote.DailyDataRepositoryImplementation
import com.ybdev.appnextdaily.repository.room_db.DailyRoomDB
import com.ybdev.appnextdaily.repository.room_db.RoomDBRepository
import com.ybdev.appnextdaily.repository.shared_preferences.MySP
import com.ybdev.appnextdaily.util.Constants.ROOM_DB_NAME
import com.ybdev.appnextdaily.util.Constants.SHARED_PREFERENCES_DB_NAME
import com.ybdev.appnextdaily.util.Constants.TWELVE_HOURS_IN_MILLI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module  {

    single {
         val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
         val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
        Retrofit.Builder()
            .baseUrl("https://apimocha.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DailyDataApi::class.java)
    }

    single {
        if (showUseRemoteRepository(get())){
            DailyDataRepositoryImplementation(get(), get(), get())
        }
        else{
        RoomDBRepository(get())
        }
    }

    single {
        Room.databaseBuilder(
            get(),
            DailyRoomDB::class.java,
            ROOM_DB_NAME
        ).build()
    }

    single{
        SharedViewModel(get())
    }

    single {
        MySP(initSP(androidContext()))
    }

    single {
        val database = get<DailyRoomDB>()
        database.getDailyDataDao()
    }

}

fun showUseRemoteRepository(sp: MySP): Boolean{
    if (sp.getLong(sp.LAST_NETWORK_CALL, 0L) + TWELVE_HOURS_IN_MILLI < System.currentTimeMillis()){
        sp.putLong(sp.LAST_NETWORK_CALL, 0L)
        return true
    }
    return false
}

fun initSP(context: Context): SharedPreferences? {
    return context.applicationContext.getSharedPreferences(SHARED_PREFERENCES_DB_NAME, Context.MODE_PRIVATE)
}