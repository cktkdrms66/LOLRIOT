package com.weather.lolapplication.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weather.lolapplication.room.dao.UserDao
import com.weather.lolapplication.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE:UserDatabase? = null

        fun getInstance(context: Context): UserDatabase?{
            if(INSTANCE == null){
                synchronized(UserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java, "user")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}