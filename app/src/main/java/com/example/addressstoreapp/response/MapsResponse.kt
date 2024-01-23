package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class MapsResponse(
    @SerializedName("googleMaps")
    val googleMaps : String,
    @SerializedName("openStreetMap")
    val openStreetMap : String,
) {
}