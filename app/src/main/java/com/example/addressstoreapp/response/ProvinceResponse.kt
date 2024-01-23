package com.example.addressstoreapp.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("area")
    val area : Int,
    @SerializedName("population")
    val population : Int,
    @SerializedName("altitude")
    val altitude : Int,
    @SerializedName("areaCode")
    val areaCode : List<Int>,
    @SerializedName("isMetropolitan")
    val isMetropolitan : Boolean,
    @SerializedName("coordinates")
    val coordinates: CoordinatesResponse,
    @SerializedName("maps")
    val maps: MapsResponse,
    @SerializedName("region")
    val region: RegionResponse,
    @SerializedName("districts")
    val districts: List<DistrictResponse>

) {
}