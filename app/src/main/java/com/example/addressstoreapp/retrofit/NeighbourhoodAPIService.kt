package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.NeighbourhoodResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NeighbourhoodAPIService(var cityId : Int,var districtName : String) {
    private val BASE_URL = "https://api.kadircolak.com/"
    private val neighbourhoodAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NeighbourhoodAPI::class.java)

    fun getNeighbourhood() : Call<List<NeighbourhoodResponse>> {
        return neighbourhoodAPI.getNeighbourhood(cityId.toString(),districtName)
    }
}