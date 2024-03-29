package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class CoordinatesResponse(
    @SerializedName("latitude")
    val latitude : Double,
    @SerializedName("longitude")
    val longitude : Double,
) {
}