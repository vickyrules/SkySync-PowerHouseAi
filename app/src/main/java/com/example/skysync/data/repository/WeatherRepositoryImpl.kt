package com.example.skysync.data.repository

import com.example.skysync.network.model.ApiResponse
import com.example.skysync.network.service.ApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    WeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): ApiResponse =
        apiService.fetchCurrentWeather(latitude, longitude)

}