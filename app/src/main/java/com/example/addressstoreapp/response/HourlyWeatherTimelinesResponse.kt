package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName
// Timelines
data class HourlyWeatherTimelinesResponse(
    @SerializedName("hourly")
    val hourlyWeatherList : List<HourlyWeatherResponse>
) {
}