package com.thinkseedsystems.weatherapp.network

import com.thinkseedsystems.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for fetching weather data.
 * Defines the API endpoints used to retrieve weather info
 * from a remote server.
 */
interface WeatherApiService {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric", // Default to metric units (Celsius)
    ): Call<WeatherResponse>

    @GET("weather")
    fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"  // default to Celsius
    ): Call<WeatherResponse>

}