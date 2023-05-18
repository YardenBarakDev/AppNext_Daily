package com.ybdev.appnextdaily.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.ybdev.appnextdaily.R
import com.ybdev.appnextdaily.SharedViewModel
import com.ybdev.appnextdaily.UIState
import com.ybdev.appnextdaily.databinding.FragmentDailyBinding
import com.ybdev.appnextdaily.model.WeeklyDataModel
import com.ybdev.appnextdaily.repository.remote.toWeeklyDataModel
import com.ybdev.appnextdaily.util.ChartLogicImplementation
import com.ybdev.appnextdaily.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyFragment : Fragment() {

    private val mViewModel by viewModel<SharedViewModel>()
    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.dailyFragmentTimelineButton.setOnClickListener {
            findNavController().navigate(R.id.action_dailyActivityFragment_to_timelineFragment)
        }
    }

    private fun observe() {
        lifecycleScope.launch(Dispatchers.Main){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mViewModel.mStateFlow.collect{
                    when(it){
                        is UIState.Loading -> binding.dailyFragmentProgressBar.visibility = View.VISIBLE
                        is UIState.Error -> showErrorState()
                        is UIState.Success -> showSuccessState(it.data.toWeeklyDataModel())
                    }
                }
            }
        }
    }

    private fun stopProgressBar(){
        binding.dailyFragmentProgressBar.apply {
            invalidate()
            visibility = View.GONE
        }
    }

    private fun showErrorState() {
        binding.dailyFragmentErrorText.apply {
            text = getString(R.string.network_issue_text)
            visibility = View.VISIBLE
        }
        stopProgressBar()
    }

    private fun showSuccessState(respond: WeeklyDataModel) {
        binding.dailyFragmentUiGroup.visibility = View.VISIBLE
        stopProgressBar()
        val logic = ChartLogicImplementation(respond, requireContext())
        binding.dailyFragmentChart.apply {
            data = logic.getBarData()
            isDragEnabled = true

            val groundSpace = 0.35f
            data.barWidth = 0.3f
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(Constants.daysArray)
                setCenterAxisLabels(true)
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                isGranularityEnabled = true
                axisMinimum = 0f
                axisLeft.apply {
                    axisMinimum = 0f
                    setDrawGridLines(false)
                    setDrawAxisLine(false)
                    setDrawLabels(false)
                }
                axisRight.apply {
                    setDrawGridLines(false)
                    setDrawAxisLine(false)
                    setDrawLabels(false)
                }
                setDrawAxisLine(false)
                setDrawGridLines(false)
            }
            groupBars(0f,groundSpace, 0F)
            description.isEnabled = false
            marker = logic.customMarker
            visibility = View.VISIBLE
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}