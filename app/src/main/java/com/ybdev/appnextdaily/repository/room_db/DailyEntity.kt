package com.ybdev.appnextdaily.repository.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ybdev.appnextdaily.repository.remote.DailyRespondModel
import com.ybdev.appnextdaily.util.Constants.DAILY_ENTITY_TABLE_NAME

@Entity(tableName = DAILY_ENTITY_TABLE_NAME)
data class DailyEntity(
   @PrimaryKey val key: String,
   val DailyRespondModel: DailyRespondModel
)
