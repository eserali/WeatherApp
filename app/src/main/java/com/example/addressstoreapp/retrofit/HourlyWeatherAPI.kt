package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.HourlyWeatherResultResponse
import com.example.addressstoreapp.response.HourlyWeatherTimelinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface HourlyWeatherAPI {
    // https://api.tomorrow.io/v4/weather/forecast?location=ankara&apikey=xD40abfLBkCSMxMbfEUUoZqgnJKb2TaF&timesteps=1h
    // base url https://api.tomorrow.io/
    // extension v4/weather/forecast
    // Query location string apikey string timesteps=1h string

    @GET("v4/weather/forecast")
    fun getHourlyWeatherList(@Query("location") cityName : String,
                             @Query("apikey") apiKey : String,
                             @Query("timesteps") timeSteps : String) : Call<HourlyWeatherResultResponse>


}