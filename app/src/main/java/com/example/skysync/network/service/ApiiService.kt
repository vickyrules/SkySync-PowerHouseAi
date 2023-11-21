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
    ): ApiResponse

    /**
     *https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     */
    @GET("data/2.5/weather")
    suspend fun fetchCurrentWeatherCitu(
        @Query("q") city: String,
    ): ApiResponse

}

//"http://openweathermap.org/img/w/" + iconcode + ".png";