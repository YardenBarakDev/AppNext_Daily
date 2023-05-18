package com.ybdev.appnextdaily.repository.room_db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ybdev.appnextdaily.repository.remote.DailyRespondModel

class Convertors {

    @TypeConverter
    fun listToJson(value: DailyRespondModel) = Gson().toJson(value)


    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, DailyRespondModel::class.java)

}