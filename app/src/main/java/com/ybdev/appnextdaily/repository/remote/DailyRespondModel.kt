package com.ybdev.appnextdaily.repository.remote

import com.ybdev.appnextdaily.model.DailyDistanceAndCal
import com.ybdev.appnextdaily.model.DailyGoalAndInfoModel
import com.ybdev.appnextdaily.model.WeeklyDataListModel
import com.ybdev.appnextdaily.model.WeeklyDataModel

data class DailyRespondModel(
    val weekly_data: List<WeeklyData?>? = null
)

data class WeeklyData(
    val daily_item: DailyItem? = null,
    val daily_data: DailyData? = null
)

data class DailyItem(
    val daily_goal: Int? = null,
    val daily_activity: Int? = null
)

data class DailyData(
    val daily_distance_meters: Int? = null,
    val daily_kcal: Int? = null
)

fun DailyRespondModel.toWeeklyDataModel(): WeeklyDataModel{
    val dailyItemList = mutableListOf<DailyGoalAndInfoModel>()
    val dailyDataList = mutableListOf<DailyDistanceAndCal>()
     weekly_data?.forEach {
         dailyItemList.add(DailyGoalAndInfoModel(it?.daily_item?.daily_goal ?: 0,it?.daily_item?.daily_activity ?: 0))
         dailyDataList.add(DailyDistanceAndCal(it?.daily_data?.daily_distance_meters ?: 0,it?.daily_data?.daily_kcal ?: 0))
    }
    return WeeklyDataModel(
        dailyItemList = dailyItemList,
        dailyDataList = dailyDataList
    )
}

fun DailyRespondModel.toWeeklyDataListModel(): List<WeeklyDataListModel>{
    return weekly_data?.map {
        WeeklyDataListModel(
            numOfSteps = it?.daily_item?.daily_activity ?: 0,
            stepsGoal = it?.daily_item?.daily_goal ?: 0,
            kcal = it?.daily_data?.daily_kcal ?: 0,
            distance= it?.daily_data?.daily_distance_meters ?: 0
        )
    } ?: listOf()
}