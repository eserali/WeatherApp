package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class AllCitiesResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("data")
    val provinceResponseList : List<ProvinceResponse>
) {
}