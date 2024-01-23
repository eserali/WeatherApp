package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("area")
    val area : Int,
    @SerializedName("population")
    val population : Int,
    @SerializedName("province")
    val province : String,
) {
}