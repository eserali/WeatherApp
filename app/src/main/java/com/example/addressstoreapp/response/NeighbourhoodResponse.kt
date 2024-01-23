package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class NeighbourhoodResponse(
    @SerializedName("CITY")
    val city : String,
    @SerializedName("DISTRICT")
    val districtOfCity : String,
    @SerializedName("TOWN")
    val neighbourhood : String,
) {
}