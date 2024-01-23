package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName
// Hourly
data class HourlyWeatherResponse(
    var time: String,
    @SerializedName("values")
    val hourlyWeatherValuesResponse: HourlyWeatherValuesResponse
) {
}