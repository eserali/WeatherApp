package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.NeighbourhoodResponse
import com.example.addressstoreapp.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {
    // https://api.collectapi.com/weather/getWeather?data.lang=tr&data.city=berlin
    // base https://api.collectapi.com/
    // extension weather/getWeather?data.lang=tr&data.city=berlin
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("weather/getWeather")
    fun getCityWeather( @Header("Authorization") auth : String,
                        @Query("data.city") cityName : String,
                        @Query("data.lang") language : String
                        ) : Call<WeatherResponse>
}