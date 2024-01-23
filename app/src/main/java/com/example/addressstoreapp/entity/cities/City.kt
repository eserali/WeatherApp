package com.example.addressstoreapp.entity.cities

class City(var id: Int,var name: String,var regionName: String,var coordinates: ArrayList<KeyValues>,
           var maps: ArrayList<KeyValues>,var districts : ArrayList<District>) {

}

class District(var id: Int,var name: String) {
    var neighbourhoods : List<Neighbourhood>? = null

    fun setNeighbourhood(neighbourhoods : List<Neighbourhood>) {
        this.neighbourhoods = neighbourhoods
    }
}


class Neighbourhood(var id : String, var name : String) {

    /*
    var id: Int? = 0
    var name : String? = null*/
}

class KeyValues(var key : String, var value : String)
{
    /*
    var key: String? = null
    var value : String? = null*/
}