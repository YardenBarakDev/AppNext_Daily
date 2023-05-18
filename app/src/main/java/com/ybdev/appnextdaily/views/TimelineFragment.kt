package com.ybdev.appnextdaily.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ybdev.appnextdaily.adapters.DailyStatusAdapter
import com.ybdev.appnextdaily.SharedViewModel
import com.ybdev.appnextdaily.databinding.FragmentTimelineBinding
import com.ybdev.appnextdaily.repository.remote.toWeeklyDataListModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimelineFragment : Fragment() {

    private var _binding : FragmentTimelineBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by viewModel<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        binding.fragmentTimelineBackLayout.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setRecyclerView() {
        binding.fragmentTimelineRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DailyStatusAdapter().apply { setList(mViewModel.getDailyRespondModel()?.toWeeklyDataListModel()) }
        }
    }
}