package com.example.addressstoreapp.manager

import android.content.Context
import androidx.room.Room
import com.example.addressstoreapp.room.UserDao
import com.example.addressstoreapp.room.UserDatabase
import com.example.addressstoreapp.room.UserEntity


class UserManager(var context: Context) {
    private lateinit var db : UserDatabase
    private lateinit var userDao : UserDao
    fun getOrSetUser() : UserEntity {
        db = Room.databaseBuilder(context,UserDatabase::class.java,"User").allowMainThreadQueries().build()
        userDao = db.userDao()

        if(userDao.getUser().isEmpty()) {
            val user = UserEntity("FirstName","LastName","")
            userDao.insertUser(user)
        }
        return userDao.getUser()[0]
    }
}