package com.example.skysync.data.repository

import com.example.skysync.network.model.ApiResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): ApiResponse
}