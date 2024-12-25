package com.example.mvplovecalculator.data.repository

import com.example.mvplovecalculator.BuildConfig
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.network.ApiService
import javax.inject.Inject

class LoveCalculatorRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun calculateLovePercentage(
        firstName: String,
        secondName: String,
        callback: retrofit2.Callback<CalculatedResult>
    ) {
        apiService.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = BuildConfig.KEY,
            host = BuildConfig.HOST
        ).enqueue(callback)
    }
}