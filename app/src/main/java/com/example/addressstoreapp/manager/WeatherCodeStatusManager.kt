package com.example.addressstoreapp.manager

import com.example.addressstoreapp.R

class WeatherCodeStatusManager(var weatherCode : Int) {
    val clearWeatherCodeList = arrayListOf<Int>(1000,1100,2101,2106)
    val cloudsWeatherCodeList = arrayListOf<Int>(1101,1102,1001,1103,2100,2000,2102,2103,2107,2108)
    val rainWeatherCodeList = arrayListOf<Int>(4000,4001,4200,4201,6000,6001,6200,6201,8000)
    val snowWeatherCodeList = arrayListOf<Int>(5000,5001,5100,5101,7000,7101,7102)

    fun detectWeatherCode() : Int {
        val clearListSize = clearWeatherCodeList.size - 1
        val cloudsListSize = cloudsWeatherCodeList.size - 1
        val rainListSize = rainWeatherCodeList.size - 1
        val snowListSize = snowWeatherCodeList.size - 1
        for(i in 0..clearListSize) {
            if (weatherCode == clearWeatherCodeList[i]) {
                return R.drawable.sunicon
            }
        }
        for(i in 0..cloudsListSize) {
            if (weatherCode == cloudsWeatherCodeList[i]) {
                return R.drawable.cloudicon
            }
        }
        for(i in 0..rainListSize) {
            if (weatherCode == rainWeatherCodeList[i]) {
                return R.drawable.rainicon
            }
        }
        for(i in 0..snowListSize) {
            if (weatherCode == snowWeatherCodeList[i]) {
                return R.drawable.snowicon
            }
        }
        return 0
    }

}
