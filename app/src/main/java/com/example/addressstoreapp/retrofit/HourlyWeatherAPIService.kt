package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.HourlyWeatherResultResponse
import com.example.addressstoreapp.response.HourlyWeatherTimelinesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HourlyWeatherAPIService(var cityName : String) {
    private val BASE_URL = "https://api.tomorrow.io/"
    private val apiKey = "6MwSd1MFGvQ1SCaZ7qUbCzlkQsTi2SHW"
    private val timeSteps = "1h"
    private val hourlyWeatherAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HourlyWeatherAPI::class.java)

    fun getHourlyWeatherList() : Call<HourlyWeatherResultResponse> {
        return hourlyWeatherAPI.getHourlyWeatherList(cityName,apiKey,timeSteps)
    }
}