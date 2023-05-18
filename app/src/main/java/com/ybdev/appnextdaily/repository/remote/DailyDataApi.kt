package com.ybdev.appnextdaily.repository.remote

import retrofit2.http.GET

interface DailyDataApi {

    @GET("nextandroid/daily_data")
    suspend fun fetchWeeklyData() : DailyRespondModel
}