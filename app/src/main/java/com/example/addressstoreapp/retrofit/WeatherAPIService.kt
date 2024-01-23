package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.NeighbourhoodResponse
import com.example.addressstoreapp.response.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService(var cityName : String,var language : String) {
    private val BASE_URL = "https://api.collectapi.com/"
    val auth = "apikey 4Sr5KPrn1upQXzAx6zu8hv:4COIVgcVaTqMbj7yo74XsV"
    private val weatherAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getWeather() : Call<WeatherResponse> {
        return weatherAPI.getCityWeather(auth,cityName,language)
    }
}