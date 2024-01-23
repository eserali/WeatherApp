package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.AllCitiesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface AllCitiesAPI {
    // https://turkiyeapi.dev/api/v1/provinces
    // Ext api/v1/provinces
    // Base https://turkiyeapi.dev/

    @GET("api/v1/provinces")
    fun getAllCities() : Call<AllCitiesResponse>
}