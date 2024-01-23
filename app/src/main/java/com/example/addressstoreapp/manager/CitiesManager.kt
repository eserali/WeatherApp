package com.example.addressstoreapp.manager

import android.os.StrictMode
import com.example.addressstoreapp.entity.cities.City
import com.example.addressstoreapp.entity.cities.District
import com.example.addressstoreapp.entity.cities.KeyValues
import com.example.addressstoreapp.entity.cities.Neighbourhood
import com.example.addressstoreapp.response.NeighbourhoodResponse
import com.example.addressstoreapp.retrofit.AllCitiesAPIService
import com.example.addressstoreapp.retrofit.NeighbourhoodAPIService
import java.lang.Exception
import java.util.UUID

class CitiesManager() {

    companion object {
        private var cities = ArrayList<City>()

        fun getCities() : List<City> {
            if(cities.size == 0) {
                val allCitiesAPIService = AllCitiesAPIService()
                val tempCities = ArrayList<City>()

                var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                var allCities = allCitiesAPIService.getAllCities().execute()

                if(allCities.body()!!.status.equals("OK")) {
                    for(provinceResponse in allCities.body()!!.provinceResponseList) {
                        val districtList = ArrayList<District>()
                        var coordinatesList = ArrayList<KeyValues>()
                        coordinatesList.add(KeyValues("X",provinceResponse.coordinates.latitude.toString()))
                        coordinatesList.add(KeyValues("Y",provinceResponse.coordinates.longitude.toString()))

                        var mapsList = ArrayList<KeyValues>()
                        mapsList.add(KeyValues("googleMaps",provinceResponse.maps.googleMaps))
                        mapsList.add(KeyValues("openStreetMap",provinceResponse.maps.openStreetMap))

                        for(districtResponse in provinceResponse.districts) {
                            var district = District(districtResponse.id,districtResponse.name)
                            districtList.add(district)
                        }

                        var city = City(provinceResponse.id,
                            provinceResponse.name,
                            provinceResponse.region.regionTr,
                            coordinatesList,
                            mapsList,districtList
                        )

                        tempCities.add(city)
                    }
                }

                cities = tempCities
            }
           return cities
        }

        fun getDistrict (cityId : Int): List<District>{

            var districts = getCities().single { it.id == cityId }.districts

            return districts
        }

        fun getNeighbourhood (cityId : Int, districtName:String): List<Neighbourhood>{


            var selectedCity = getCities().single { it.id == cityId }
            val selectedDistrict = selectedCity.districts.singleOrNull {
                it.name == districtName
            } ?: throw Exception("Seçmiş olduğunuz ilçeye ait kayıt bulunamamıştır.")

            val neighbourhoodList = ArrayList<Neighbourhood>()
            if (selectedDistrict.neighbourhoods == null)
            {
                var neighbourhoodResponseList : List<NeighbourhoodResponse> = NeighbourhoodAPIService(cityId,districtName.uppercase()).getNeighbourhood().execute().body()!!
                for(neighbourhoodResponse in neighbourhoodResponseList) {
                    neighbourhoodList.add(Neighbourhood(UUID.randomUUID().toString(),neighbourhoodResponse.neighbourhood))
                }

                selectedDistrict.setNeighbourhood(neighbourhoodList)
            }
            else
            {
                return selectedDistrict.neighbourhoods!!
            }

                return neighbourhoodList
        }


    }
}

