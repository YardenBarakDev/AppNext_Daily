package com.ybdev.appnextdaily.repository.room_db

import com.ybdev.appnextdaily.UIState
import com.ybdev.appnextdaily.repository.remote.DailyDataRepository
import kotlinx.coroutines.flow.flow

class RoomDBRepository(private val repository: DailyRoomDB?) : DailyDataRepository {

    override suspend fun fetchWeeklyData() = flow {
        repository?.getDailyDataDao()?.fetchData()?.collect{
            it?.let {
                emit(UIState.Success(it.DailyRespondModel))
            }
        }
    }

}