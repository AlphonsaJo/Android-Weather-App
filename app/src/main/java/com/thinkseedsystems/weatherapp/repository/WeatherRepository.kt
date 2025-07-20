package com.thinkseedsystems.weatherapp.repository

import retrofit2.Call 
import android.util.Log
import com.thinkseedsystems.weatherapp.model.WeatherResponse
import com.thinkseedsystems.weatherapp.network.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Repository class responsible for handling API interactions for weather data.
 */
class WeatherRepository {

    init {
        try {
            // Retrofit setup for network requests
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/") // OpenWeather API base URL
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON response to Kotlin objects
                .build()

            // Create API service instance
            api = retrofit.create(WeatherApiService::class.java)
            Log.d("WeatherRepository", "Retrofit instance created successfully.")

        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error initializing Retrofit: ${e.message}", e)
            throw RuntimeException("Failed to initialize Retrofit", e) // Crash the app if Retrofit setup fails
        }
    }

    fun getWeather(city: String, apiKey: String) = try {
        Log.d("WeatherRepository", "Fetching weather data for city: $city")
        api.getWeather(city, apiKey)
    } catch (e: Exception) {
        Log.e("WeatherRepository", "Error fetching weather data: ${e.message}", e)
        throw RuntimeException("Failed to fetch weather data", e) // Rethrow exception to handle it at a higher level
    }

    fun getWeatherByCoordinates(lat: Double, lon: Double, apiKey: String): Call<WeatherResponse> {
        return api.getWeatherByCoordinates(lat, lon, apiKey)
    }

}
