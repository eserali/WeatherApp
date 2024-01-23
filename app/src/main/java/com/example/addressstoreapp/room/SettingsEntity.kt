package com.example.addressstoreapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingsEntity(

    @ColumnInfo(name = "Name")
    val name : String,
    @ColumnInfo(name = "value1")
    val value1 : String? = null,
    @ColumnInfo(name = "value2")
    val value2 : String? = null,
    @ColumnInfo(name = "value3")
    val value3 : String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1
}