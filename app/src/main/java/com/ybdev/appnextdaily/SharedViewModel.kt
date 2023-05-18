package com.ybdev.appnextdaily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ybdev.appnextdaily.repository.remote.DailyDataRepository
import com.ybdev.appnextdaily.repository.remote.DailyRespondModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: DailyDataRepository) : ViewModel(){

    private val _mStateflow = MutableStateFlow<UIState>(UIState.Loading)
    val mStateFlow = _mStateflow.asStateFlow()
    private var mDailyRespondModel : DailyRespondModel? = null

    init {
        fetchData()
    }

    private fun fetchData(){
        if (mDailyRespondModel == null){
            viewModelScope.launch(Dispatchers.IO){
                repository.fetchWeeklyData().collect { state ->
                    if (state is UIState.Success){
                        mDailyRespondModel = state.data
                    }
                    _mStateflow.emit(state)
                }
            }
        }
    }

    fun getDailyRespondModel() = mDailyRespondModel
}