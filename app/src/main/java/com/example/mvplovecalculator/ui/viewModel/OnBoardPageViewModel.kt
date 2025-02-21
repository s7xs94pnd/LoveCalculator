package com.example.mvplovecalculator.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvplovecalculator.R

class OnBoardPageViewModel : ViewModel() {

    private val _title1 = MutableLiveData<String>()
    val title1: LiveData<String> get() = _title1

    private val _title2 = MutableLiveData<String>()
    val title2: LiveData<String> get() = _title2

    private val _animationResId = MutableLiveData<Int>()
    val animationResId: LiveData<Int> get() = _animationResId

    fun updateContent(position: Int) {
        when (position) {
            0 -> {
                _title1.value = "Have a good time"
                _title2.value = "You should take the time to help those\n who need you"
                _animationResId.value = R.raw.home
            }
            1 -> {
                _title1.value = "Cherishing love"
                _title2.value = "It is now no longer possible for\n you to cherish love"
                _animationResId.value = R.raw.home
            }
            2 -> {
                _title1.value = "Have a breakup?"
                _title2.value = "We have made the correction for you don't worry \nMaybe someone is waiting for you!"
                _animationResId.value = R.raw.home
            }
        }
    }
}