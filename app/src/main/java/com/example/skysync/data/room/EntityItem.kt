package com.example.skysync.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityItem(
    @PrimaryKey
    val name:String,
    val id: Int,
    val temp:Double,
    val minTemp:Double,
    val maxTemp:Double,
    val country:String,
    val rain:Double,
    val windSpeed:Double,
    val cloud:Int,
    val feelsLike:Double,
    val description:String,
    val iconCode:String,
    val lat:Double,
    val lon:Double,

   )