package com.ybdev.appnextdaily.repository.remote

import com.ybdev.appnextdaily.UIState
import com.ybdev.appnextdaily.repository.room_db.DailyEntity
import com.ybdev.appnextdaily.repository.room_db.DailyRoomDB
import com.ybdev.appnextdaily.repository.shared_preferences.MySP
import kotlinx.coroutines.flow.flow

class DailyDataRepositoryImplementation(private val api: DailyDataApi, private val sp: MySP, private val roomDB: DailyRoomDB) : DailyDataRepository {

    override suspend fun fetchWeeklyData() = flow{
        kotlin.runCatching {
            api.fetchWeeklyData()
        }.onSuccess {
            emit(UIState.Success(it))
            sp.putLong(sp.LAST_NETWORK_CALL,System.currentTimeMillis())
            roomDB.getDailyDataDao().delete()
            roomDB.getDailyDataDao().insert(DailyEntity("def_key", it))
        }.onFailure {
            emit(UIState.Error(""))
        }
    }

}