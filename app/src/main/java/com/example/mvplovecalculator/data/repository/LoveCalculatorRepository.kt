package com.example.mvplovecalculator.data.repository

import com.example.mvplovecalculator.BuildConfig
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.network.RetrofitInstance

class LoveCalculatorRepository {
    fun calculateLovePercentage(
        firstName: String,
        secondName: String,
        callback: retrofit2.Callback<CalculatedResult>
    ) {
        RetrofitInstance.api.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = BuildConfig.KEY,
            host = BuildConfig.HOST
        ).enqueue(callback)
    }
}
