package com.ybdev.appnextdaily.repository.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DailyEntity::class], version = 1)
@TypeConverters(Convertors::class)
abstract class DailyRoomDB: RoomDatabase(){

    abstract fun getDailyDataDao(): DailyDataDao
}