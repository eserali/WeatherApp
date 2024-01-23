package com.example.addressstoreapp.manager


import android.content.Context
import androidx.room.Room
import com.example.addressstoreapp.enum.SettingsName
import com.example.addressstoreapp.room.SettingsDao
import com.example.addressstoreapp.room.SettingsDatabase
import com.example.addressstoreapp.room.SettingsEntity


class SettingsManager(var context: Context) {
    private lateinit var db : SettingsDatabase
    private lateinit var settingsDao: SettingsDao

    fun getOrAdd(name : Enum<SettingsName>) : SettingsEntity {
        db = Room.databaseBuilder(context,SettingsDatabase::class.java,"Settings").allowMainThreadQueries().build()
        settingsDao = db.settingsDao()

        if(settingsDao.get(name.toString()) == null) {

            val value1 = "7"
            val value2 = "18"
            val value3 = "24"
            val settings = SettingsEntity(name.toString(), value1, value2, value3)
            settingsDao.insert(settings)

        }
        return settingsDao.get(name.toString())
    }

    fun update(name : Enum<SettingsName>, value1 : String, value2 : String, value3 : String) {
        settingsDao.updateSettings(name.toString(),value1,value2,value3)
    }
}