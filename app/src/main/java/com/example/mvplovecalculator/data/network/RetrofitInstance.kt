package com.example.mvplovecalculator.data.network

import com.example.mvplovecalculator.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val api = retrofit.create(ApiService::class.java)
}
