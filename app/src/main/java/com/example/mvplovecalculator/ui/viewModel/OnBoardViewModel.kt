package com.example.mvplovecalculator.ui.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _isOnboardingComplete = MutableLiveData(false)
    val isOnboardingComplete: LiveData<Boolean> get() = _isOnboardingComplete

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun completeOnboarding() {
        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
        _isOnboardingComplete.value = true
    }
}
