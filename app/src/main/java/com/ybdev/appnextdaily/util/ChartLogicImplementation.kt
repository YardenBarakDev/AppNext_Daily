package com.ybdev.appnextdaily.util

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.ybdev.appnextdaily.R
import com.ybdev.appnextdaily.model.WeeklyDataModel

class ChartLogicImplementation(weeklyDataModel: WeeklyDataModel,val  context: Context) {

    private val dailyGoalPair = Pair(ArrayList<BarEntry>(), ArrayList<BarEntry>())

    init {
        weeklyDataModel.dailyItemList.forEachIndexed { index, item ->
            dailyGoalPair.first.add(BarEntry((index).toFloat(),item.dailyGoal.toFloat()))
            dailyGoalPair.second.add(BarEntry(((index)).toFloat(),item.dailyActivity.toFloat()))
        }
    }

    fun getBarData(): BarData{
        val barDataSet2 = BarDataSet(dailyGoalPair.first,"Daily goal")
        barDataSet2.color =  ResourcesCompat.getColor(context.resources, R.color.green, null)

        val barDataSet1 = BarDataSet(dailyGoalPair.second,"Activity")
        barDataSet1.color =  ResourcesCompat.getColor(context.resources, R.color.blue, null);

        return BarData(barDataSet1, barDataSet2)
    }


}