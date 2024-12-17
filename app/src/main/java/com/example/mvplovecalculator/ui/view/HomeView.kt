package com.example.mvplovecalculator.ui.view

import com.example.mvplovecalculator.data.model.CalculatedResult

interface HomeView {
    fun showLoading()
    fun hideLoading()
    fun showResult(result: CalculatedResult)
    fun showValidationErrors()
    fun showError(message: String)
}