package com.example.mvplovecalculator.presenter

import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import com.example.mvplovecalculator.ui.view.HomeView

class HomePresenter(private val view: HomeView, private val repository: LoveCalculatorRepository) {

    fun onCalculateClicked(firstName: String, secondName: String) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            view.showValidationErrors()
            return
        }
        view.showLoading()
        repository.calculateLovePercentage(
            firstName,
            secondName,
            object : retrofit2.Callback<CalculatedResult> {
                override fun onResponse(
                    call: retrofit2.Call<CalculatedResult>,
                    response: retrofit2.Response<CalculatedResult>
                ) {
                    view.hideLoading()
                    if (response.isSuccessful && response.body() != null) {
                        view.showResult(response.body()!!)
                    } else {
                        view.showError("Ошибка: ${response.message()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<CalculatedResult>, t: Throwable) {
                    view.hideLoading()
                    view.showError("Ошибка сети: ${t.localizedMessage}")
                }
            })
    }
}