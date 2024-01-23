package com.example.addressstoreapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(

    @ColumnInfo(name = "Name")
    val firstName : String? = null,
    @ColumnInfo(name = "Surname")
    val surName : String? = null,
    @ColumnInfo(name = "Selected Cities")
    val selectedCities : String? = null,

) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1
}