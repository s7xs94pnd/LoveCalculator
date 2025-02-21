package com.example.mvplovecalculator.ui.fragments.onboard

import android.os.Bundle
import android.view.View
import com.example.mvplovecalculator.databinding.FragmentViewPagerOnBoardBinding
import com.example.mvplovecalculator.ui.fragments.BaseFragment
import com.example.mvplovecalculator.ui.viewModel.OnBoardPageViewModel


class ViewPagerOnBoardFragment : BaseFragment<FragmentViewPagerOnBoardBinding,OnBoardPageViewModel>(FragmentViewPagerOnBoardBinding::inflate,OnBoardPageViewModel::class.java) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt("onBoardPosition", 0)
        viewModel.updateContent(position)
        observeViewModel()
    }

    private fun observeViewModel() = with(binding) {
        viewModel.title1.observe(viewLifecycleOwner) { title ->
            title1.text = title
        }

        viewModel.title2.observe(viewLifecycleOwner) { subtitle ->
            title2.text = subtitle
        }

        viewModel.animationResId.observe(viewLifecycleOwner) { animation ->
            lottie.setAnimation(animation)
            lottie.playAnimation()
        }
    }
}