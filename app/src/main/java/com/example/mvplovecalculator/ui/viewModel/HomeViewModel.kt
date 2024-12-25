package com.example.mvplovecalculator.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvplovecalculator.data.database.dao.LoveResultDao
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.database.entities.LoveResultEntity
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import com.example.mvplovecalculator.data.mappers.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LoveCalculatorRepository,
    private val dao: LoveResultDao
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _result = MutableLiveData<CalculatedResult?>()
    val result: MutableLiveData<CalculatedResult?> get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _history = MutableLiveData<List<LoveResultEntity>>()
    val history: LiveData<List<LoveResultEntity>> get() = _history

    // Метод для вычисления процента любви
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
                        val result = response.body()!!
                        _result.value = result
                        saveResultToDatabase(result)
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

    private fun saveResultToDatabase(result: CalculatedResult) {
        val loveResultEntity = result.toEntity()
        viewModelScope.launch {
            dao.insert(loveResultEntity)
        }
    }

    fun clearResult() {
        _result.value = null
    }

    fun loadHistory() {
        viewModelScope.launch {
            _history.value = dao.getAllResults()
        }
    }
}