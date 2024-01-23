package com.example.addressstoreapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(vararg user : UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser() : List<UserEntity>
}