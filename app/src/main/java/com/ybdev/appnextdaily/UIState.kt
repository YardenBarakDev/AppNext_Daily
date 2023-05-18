package com.ybdev.appnextdaily

import com.ybdev.appnextdaily.repository.remote.DailyRespondModel
import com.ybdev.appnextdaily.repository.room_db.DailyEntity


sealed interface UIState{
    class Success(val data: DailyRespondModel) : UIState
    data class Error(val message: String) : UIState
    object Loading : UIState
}
