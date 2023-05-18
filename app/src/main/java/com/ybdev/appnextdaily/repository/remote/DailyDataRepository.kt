package com.ybdev.appnextdaily.repository.remote

import com.ybdev.appnextdaily.UIState
import kotlinx.coroutines.flow.Flow

interface DailyDataRepository {

    suspend fun fetchWeeklyData(): Flow<UIState>
}