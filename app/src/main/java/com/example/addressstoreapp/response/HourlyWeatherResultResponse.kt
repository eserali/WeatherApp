package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class HourlyWeatherResultResponse(
    @SerializedName("timelines")
    val hourlyWeatherTimelinesResponse: HourlyWeatherTimelinesResponse,
    val location: HourlyLocationResponse
) {
}