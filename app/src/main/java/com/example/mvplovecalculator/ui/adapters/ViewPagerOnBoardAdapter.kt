package com.example.mvplovecalculator.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvplovecalculator.ui.fragments.onboard.ViewPagerOnBoardFragment

class ViewPagerOnBoardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int) = ViewPagerOnBoardFragment().apply {
        arguments = Bundle().apply { putInt("onBoardPosition", position) }
    }
}
