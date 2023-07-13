package com.example.yeni.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yeni.adapter.UserDao
import com.example.yeni.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun userDao():UserDao
}