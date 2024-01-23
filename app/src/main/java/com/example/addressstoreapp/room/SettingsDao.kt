package com.example.addressstoreapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Insert
    fun insert(vararg setting : SettingsEntity)

    @Query("SELECT * FROM SettingsEntity WHERE name = :name")
    fun get(vararg name : String) : SettingsEntity

    @Query("UPDATE SettingsEntity SET value1 = :value1,value2 = :value2,value3 = :value3 WHERE name = :name")
    fun updateSettings(name : String,value1 : String,value2 : String, value3 : String)
}