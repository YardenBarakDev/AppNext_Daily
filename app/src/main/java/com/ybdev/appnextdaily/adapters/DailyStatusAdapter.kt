package com.ybdev.appnextdaily.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ybdev.appnextdaily.R
import com.ybdev.appnextdaily.databinding.DailyStatusItemBinding
import com.ybdev.appnextdaily.model.WeeklyDataListModel
import com.ybdev.appnextdaily.util.Constants

class DailyStatusAdapter : RecyclerView.Adapter<DailyStatusAdapter.WeeklyDataViewHolder>(){

    private var mWeeklyDataList = ArrayList<WeeklyDataListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyDataViewHolder {
        return WeeklyDataViewHolder(
            DailyStatusItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeeklyDataViewHolder, position: Int) {
        holder.bind(mWeeklyDataList[position], position)
    }

    override fun getItemCount(): Int = mWeeklyDataList.size

    fun setList(newList: List<WeeklyDataListModel>?){
        newList?.let {
            mWeeklyDataList.addAll(newList)
        }
    }

    inner class WeeklyDataViewHolder(private val binding: DailyStatusItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: WeeklyDataListModel, position: Int){
            with(binding){
                dailyStatusItemNumOfSteps.text = data.numOfSteps.toString()
                dailyStatusItemNumOfGoalSteps.text = "/${data.stepsGoal}"
                dailyStatusItemNumOfKcal.text = "${data.kcal} KCAL"
                dailyStatusItemDayOfMonthText.text = "${position + 1}"
                dailyStatusItemShortDayNameText.text = Constants.daysArray.getOrNull(position) ?: ""
                dailyStatusItemKm.text = getDistanceText(data.distance)
                dailyStatusItemProgressIndicator.progress = calculatePercentage(data.stepsGoal, data.numOfSteps)
                if (data.numOfSteps > data.stepsGoal) {
                    dailyStatusItemNumOfSteps.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
                    dailyStatusItemTopCircle.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.green_circle))
                    dailyStatusItemBottomCircle.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.green_circle))
                    dailyStatusItemProgressIndicator.setIndicatorColor(ContextCompat.getColor(itemView.context, R.color.green))
                }
            }
        }

        private fun getDistanceText(distance: Int): CharSequence {
            return when{
                distance < 1000 -> "$distance M"
                else -> "${distance.toString().substring(0,1)} KM"
            }
        }

        private fun calculatePercentage(goal: Int, numOfSteps: Int): Int {
            val percentage = ((numOfSteps.toFloat()/goal.toFloat()) * 100)
            return if (percentage > 100) 100 else percentage.toInt()
        }
    }

}