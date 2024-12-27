package com.example.mvplovecalculator.ui.fragments

import android.os.Bundle

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvplovecalculator.databinding.FragmentHistoryBinding
import com.example.mvplovecalculator.ui.adapters.HistoryAdapter
import com.example.mvplovecalculator.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding, HomeViewModel>(
    FragmentHistoryBinding::inflate,
    HomeViewModel::class.java
) {

    private lateinit var adapter: HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadHistory()
    }

    override fun setupObservers() {
        viewModel.history.observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
        }
    }

    override fun setupClickListeners() {
        binding.btnSort.setOnClickListener {
            viewModel.sortHistoryByPercentage()
        }
    }

    override fun initializeRV() {
        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    }
}