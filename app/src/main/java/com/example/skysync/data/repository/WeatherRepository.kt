package com.example.skysync.data.repository

import com.example.skysync.data.room.EntityItem
import com.example.skysync.network.model.ApiResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun saveCurrentWeather(latitude: Double, longitude: Double)
    suspend fun getCurrentWeather():EntityItem?
    suspend fun saveCurrentWeatherByCity(city: String): Flow<EntityItem>
    fun getAllItems(): Flow<List<EntityItem>>

}