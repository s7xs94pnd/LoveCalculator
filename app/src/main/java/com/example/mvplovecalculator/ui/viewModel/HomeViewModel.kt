package com.example.mvplovecalculator.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: LoveCalculatorRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _result = MutableLiveData<CalculatedResult>()
    val result: LiveData<CalculatedResult> get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun calculateLovePercentage(firstName: String, secondName: String) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            _error.value = "Введите оба имени"
            return
        }

        _loading.value = true

        repository.calculateLovePercentage(
            firstName,
            secondName,
            object : Callback<CalculatedResult> {
                override fun onResponse(
                    call: Call<CalculatedResult>,
                    response: Response<CalculatedResult>
                ) {
                    _loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _result.value = response.body()
                    } else {
                        _error.value = "Ошибка: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<CalculatedResult>, t: Throwable) {
                    _loading.value = false
                    _error.value = "Ошибка сети: ${t.localizedMessage}"
                }
            }
        )
    }
}