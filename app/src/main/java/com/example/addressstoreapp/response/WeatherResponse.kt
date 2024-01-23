package com.example.addressstoreapp.response

data class WeatherResponse(
    val success: Boolean,
    val city: String,
    val result: List<WeatherResultResponse>
) {
}