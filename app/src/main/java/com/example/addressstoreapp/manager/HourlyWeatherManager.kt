package com.example.addressstoreapp.manager

import android.os.StrictMode
import com.example.addressstoreapp.response.HourlyWeatherResponse
import com.example.addressstoreapp.retrofit.HourlyWeatherAPIService
import java.sql.Timestamp
import java.time.Clock
import java.util.Calendar

class HourlyWeatherManager {
    fun getHourlyWeather(cityName : String) : List<HourlyWeatherResponse>{
        val hourlyWeatherArrayList = ArrayList<HourlyWeatherResponse>()
        val hourlyWeatherAPIService = HourlyWeatherAPIService(cityName)
        val hourlyWeatherResult = hourlyWeatherAPIService.getHourlyWeatherList().execute()

        if(hourlyWeatherResult.body() != null) {
            var currentHour = Calendar.getInstance().time.hours

            for (i in 3..26) {

                if(currentHour >= 24) {
                    currentHour = 0
                    hourlyWeatherResult.body()!!.hourlyWeatherTimelinesResponse.hourlyWeatherList[i].time = currentHour.toString().padStart(2,'0')

                }
                else {
                    hourlyWeatherResult.body()!!.hourlyWeatherTimelinesResponse.hourlyWeatherList[i].time = currentHour.toString().padStart(2,'0')

                }
                currentHour++

                hourlyWeatherArrayList.add(hourlyWeatherResult.body()!!.hourlyWeatherTimelinesResponse.hourlyWeatherList[i])
            }
            //return hourlyWeatherResult.body()!!.hourlyWeatherTimelinesResponse.hourlyWeatherList
            return hourlyWeatherArrayList
        } else {
            throw Exception("No info for daily weather")
        }

    }
}