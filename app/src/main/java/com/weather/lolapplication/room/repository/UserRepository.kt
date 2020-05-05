package com.weather.lolapplication.room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.weather.lolapplication.room.database.UserDatabase
import com.weather.lolapplication.room.entity.User
import java.lang.Exception

class UserRepository(application: Application){

    private val userDatabase = UserDatabase.getInstance(application)!!
    private val userDao = userDatabase.userDao()
    private val users : LiveData<List<User>> = userDao.getAll()

    fun getAll():LiveData<List<User>>{
        return users
    }

    fun insert(user:User){
        try{
            Thread(Runnable {
                userDao.insert(user)
            }).start()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun delete(user:User){
        try{
            Thread(Runnable {
                userDao.delete(user)
            }).start()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}