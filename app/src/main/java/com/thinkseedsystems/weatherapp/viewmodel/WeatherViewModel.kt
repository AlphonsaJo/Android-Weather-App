package com.thinkseedsystems.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinkseedsystems.weatherapp.model.WeatherResponse
import com.thinkseedsystems.weatherapp.repository.WeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {

    // Repository instance for making API calls
    private val repository = WeatherRepository()

    // LiveData to store weather response data
    private val _weatherData = MutableLiveData<WeatherResponse>()

    // Exposes weather data as immutable LiveData to be observed by UI components.
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    fun fetchWeather(city: String, apiKey: String) {
        Log.d("WeatherViewModel", "Fetching weather for city: $city")

        try {
            repository.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        _weatherData.value = response.body()
                        Log.d("WeatherViewModel", "Weather data received: ${response.body()}")
                    } else {
                        Log.w("WeatherViewModel", "API response unsuccessful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("WeatherViewModel", "Failed to fetch weather data: ${t.message}", t)
                }
            })
        } catch (e: Exception) {
            Log.e("WeatherViewModel", "Error fetching weather data: ${e.message}", e)
        }
    }

    // Fetch Weather by coordinates
    fun fetchWeatherByCoordinates(lat: Double, lon: Double, apiKey: String) {
        repository.getWeatherByCoordinates(lat, lon, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    Log.w("WeatherViewModel", "API response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("WeatherViewModel", "Failed to fetch weather data: ${t.message}", t)
            }
        })
    }

}
