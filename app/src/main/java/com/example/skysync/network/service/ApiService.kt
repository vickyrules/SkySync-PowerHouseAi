package com.example.skysync.network.service

import com.example.skysync.network.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    /**
     *https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
     */
    @GET("data/2.5/weather")
    suspend fun fetchCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
    ): ApiResponse

    /**
     *https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     */
    @GET("data/2.5/weather")
    suspend fun fetchCurrentWeatherBYCity(
        @Query("q") city: String,
        @Query("units") unit: String = "metric"
    ): ApiResponse


}

//"http://openweathermap.org/" + iconcode + ".png";