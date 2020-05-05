package com.weather.lolapplication.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.weather.lolapplication.room.entity.User

@Dao
interface UserDao{

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}