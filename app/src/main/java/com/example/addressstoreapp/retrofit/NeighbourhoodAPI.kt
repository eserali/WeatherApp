package com.example.addressstoreapp.retrofit

import com.example.addressstoreapp.response.NeighbourhoodResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NeighbourhoodAPI {
    // https://api.kadircolak.com/Konum/JSON/API/ShowTown?plate=34&district=BAŞAKŞEHİR
    // Base https://api.kadircolak.com/

    @GET("Konum/JSON/API/ShowTown")
    fun getNeighbourhood(@Query("plate") cityId : String,
                         @Query("district") districtName: String) : Call<List<NeighbourhoodResponse>>

}