package com.ybdev.appnextdaily.model

data class WeeklyDataModel(
    val dailyItemList: MutableList<DailyGoalAndInfoModel>,
    val dailyDataList: MutableList<DailyDistanceAndCal>
)