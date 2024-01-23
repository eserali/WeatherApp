package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.AllCitiesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AllCitiesAPIService {
    private val BASE_URL = "https://turkiyeapi.dev/"
    private val allCitiesAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AllCitiesAPI::class.java)

    fun getAllCities() : Call<AllCitiesResponse> {
        return allCitiesAPI.getAllCities()
    }


}