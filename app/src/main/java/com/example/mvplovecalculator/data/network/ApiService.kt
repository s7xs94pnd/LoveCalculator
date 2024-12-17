package com.example.mvplovecalculator.data.network

import com.example.mvplovecalculator.data.model.CalculatedResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("getPercentage")
    fun getPercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,
        @Header("x-rapidapi-key") key: String,
        @Header("x-rapidapi-host") host: String
    ): Call<CalculatedResult>
}