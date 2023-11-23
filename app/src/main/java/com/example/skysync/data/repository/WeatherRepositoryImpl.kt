package com.example.skysync.data.repository

import android.content.SharedPreferences
import com.example.skysync.data.room.EntityItem
import com.example.skysync.data.room.SkySyncDatabase
import com.example.skysync.network.mapper.asEntity
import com.example.skysync.network.service.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val skySyncDatabase: SkySyncDatabase,
    private val sharedPreferences: SharedPreferences
) : WeatherRepository {
    private val gson = Gson()

    override suspend fun saveCurrentWeather(latitude: Double, longitude: Double) {
        val response = apiService.fetchCurrentWeather(latitude, longitude)
        val jsonString = gson.toJson(response.asEntity())
        sharedPreferences.edit().putString("current_data", jsonString).apply()
    }

    override suspend fun getCurrentWeather(): EntityItem? {
        val jsonString = sharedPreferences.getString("current_data", null)
        return gson.fromJson(jsonString, EntityItem::class.java)
    }

    override suspend fun saveCurrentWeatherByCity(city: String): Flow<EntityItem> {
        val response = apiService.fetchCurrentWeatherBYCity(city)
        skySyncDatabase.EntityDao().insertUpdateItem(response.asEntity())
        return skySyncDatabase.EntityDao().getItemByCity(city)
    }

    override fun getAllItems(): Flow<List<EntityItem>> {
        return skySyncDatabase.EntityDao().getAllItems()
    }


}