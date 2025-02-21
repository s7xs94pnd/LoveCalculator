package com.example.mvplovecalculator.ui.fragments.onboard

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.mvplovecalculator.R
import com.example.mvplovecalculator.databinding.FragmentOnBoardBinding
import com.example.mvplovecalculator.ui.adapters.ViewPagerOnBoardAdapter
import com.example.mvplovecalculator.ui.fragments.BaseFragment
import com.example.mvplovecalculator.ui.viewModel.OnBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : BaseFragment<FragmentOnBoardBinding, OnBoardViewModel>(
    FragmentOnBoardBinding::inflate,
    OnBoardViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager2()
        observeViewModel()
        setupListeners()
    }

    private fun initViewPager2() {
        binding.viewpager2.adapter = ViewPagerOnBoardAdapter(this)
        binding.dots.attachTo(binding.viewpager2)

        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
            }
        })
    }

    private fun observeViewModel() {
        viewModel.currentPage.observe(viewLifecycleOwner) { position ->
            updateUiForPage(position)
        }

        viewModel.isOnboardingComplete.observe(viewLifecycleOwner) { isComplete ->
            if (isComplete) {
                findNavController().navigate(R.id.action_onBoardFragment_to_homeFragment)
            }
        }
    }

    private fun setupListeners() {
        binding.tvback.setOnClickListener {
            val currentItem = binding.viewpager2.currentItem
            binding.viewpager2.setCurrentItem(currentItem - 1, true)
        }

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewpager2.currentItem
            binding.viewpager2.setCurrentItem(currentItem + 1, true)
        }

        binding.tvSkip.setOnClickListener {
            binding.viewpager2.setCurrentItem(2, true)
        }

        binding.btnStart.setOnClickListener {
            viewModel.completeOnboarding()
        }
    }

    private fun updateUiForPage(position: Int) {
        when (position) {
            0 -> {
                binding.tvback.visibility = View.GONE
                binding.tvSkip.visibility = View.VISIBLE
                binding.btnNext.visibility = View.VISIBLE
                binding.btnStart.visibility = View.GONE
            }
            1 -> {
                binding.tvback.visibility = View.VISIBLE
                binding.tvSkip.visibility = View.VISIBLE
                binding.btnNext.visibility = View.VISIBLE
                binding.btnStart.visibility = View.GONE
            }
            2 -> {
                binding.tvback.visibility = View.VISIBLE
                binding.tvSkip.visibility = View.GONE
                binding.btnNext.visibility = View.GONE
                binding.btnStart.visibility = View.VISIBLE
            }
        }
    }
}