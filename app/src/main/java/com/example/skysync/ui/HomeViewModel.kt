package com.example.skysync.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skysync.data.repository.WeatherRepository
import com.example.skysync.network.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
   lateinit var data:ApiResponse
    init {
        viewModelScope.launch {
          data =   weatherRepository.getCurrentWeather(44.34, 10.99)
            Log.v("data",data.toString())
        }
    }
}