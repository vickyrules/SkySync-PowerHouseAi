package com.example.skysync.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skysync.data.repository.WeatherRepository
import com.example.skysync.data.room.EntityItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


data class HomeUiState(val data:EntityItem?= null,val itemList: List<EntityItem> = listOf())
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        weatherRepository.getAllItems().map {HomeUiState(data = weatherRepository.getCurrentWeather() ,itemList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                weatherRepository.saveCurrentWeather(latitude = lat, longitude =  lon)
                cities.forEach { city ->
                    weatherRepository.saveCurrentWeatherByCity(city)
                }

            } catch (e: java.io.IOException) {
              Log.v("error",e.message.toString())
            } catch (e: HttpException) {
                Log.v("error",e.message.toString())
            }
        }
    }


    companion object {
        private val cities = listOf(
            "new york",
            "singapore",
            "mumbai",
            "delhi",
            "sydney",
            "melbourne"
        )


        private const val TIMEOUT_MILLIS = 5_000L

    }
}