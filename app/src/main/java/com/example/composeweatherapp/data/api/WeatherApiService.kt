package com.example.composeweatherapp.data.api

import com.example.composeweatherapp.domain.model.City
import com.example.composeweatherapp.domain.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Define an interface for the weather API service
interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("key") apiKey: String,
        @Query("aqi") aqi: String = "no"
    ): Response<Weather>

    @GET("search.json")
    suspend fun searchCities(@Query("key") apiKey: String,
                             @Query("q") query: String): Response<List<City>>
}
