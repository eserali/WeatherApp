package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class RegionResponse(
    @SerializedName("en")
    val regionEn : String,
    @SerializedName("tr")
    val regionTr : String,
) {
}