package com.example.composeweatherapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Define an interface for the weather API service
interface WeatherApiService {
    // Fetch weather data for a specified city using a GET request
    @GET("current.json")
    suspend fun getWeatherByCity(
        @Query("q") city: String, // City name as query parameter
        @Query("key") apiKey: String, // API key for authentication
        @Query("aqi") aqi: String = "no"
    ): Response<Weather>

    // Search for cities based on the query
    @GET("search.json")
    suspend fun searchCities(@Query("key") apiKey: String,
                             @Query("q") query: String): Response<List<City>>
}
