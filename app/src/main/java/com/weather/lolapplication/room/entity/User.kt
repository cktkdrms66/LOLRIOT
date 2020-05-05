package com.weather.lolapplication.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true)val id:Int?,
                @ColumnInfo(name ="name") var name:String,
                @ColumnInfo(name = "tier")var tier:String){
    constructor():this(null, "","")
}