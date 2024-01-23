package com.example.addressstoreapp.manager

import android.os.StrictMode
import com.example.addressstoreapp.response.WeatherResultResponse
import com.example.addressstoreapp.retrofit.WeatherAPIService

class WeatherManager {
    fun getWeather(cityName : String,language : String) : List<WeatherResultResponse>{
        val weatherAPIService = WeatherAPIService(cityName,language)

        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var weatherResult = weatherAPIService.getWeather().execute()

        if(weatherResult.body() != null && weatherResult.body()!!.success) {
            return weatherResult.body()!!.result.map{
                WeatherResultResponse(
                    it.date,
                    it.day,
                    it.icon,
                    it.description,
                    it.status,
                    it.degree.split(".").first(),
                    (if(it.min == "") "0" else it.min),
                    (if(it.max == "") "0" else it.max),
                    it.night,
                    it.humidity) }

        } else {
            throw Exception("Aradığınız ile ait hava durumu bulunamadı")
        }

    }
}