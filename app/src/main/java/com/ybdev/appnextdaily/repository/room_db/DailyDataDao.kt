package com.ybdev.appnextdaily.repository.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ybdev.appnextdaily.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyDataDao {

    @Query("SELECT * FROM ${Constants.DAILY_ENTITY_TABLE_NAME}")
    fun fetchData() : Flow<DailyEntity?>

    @Query("DELETE FROM ${Constants.DAILY_ENTITY_TABLE_NAME}")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg dailyEntity: DailyEntity)
}