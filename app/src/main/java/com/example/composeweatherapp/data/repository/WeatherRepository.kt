package com.example.composeweatherapp.data.repository

import com.example.composeweatherapp.data.api.WeatherApiService
import com.example.composeweatherapp.domain.model.City
import com.example.composeweatherapp.domain.model.Weather
import javax.inject.Inject

// Repository for managing weather data
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApiService
) {
    // Fetch weather data and return a Result wrapper
    suspend fun fetchWeather(city: String): Result<Weather> {
        return try {
            val response = weatherApi.getWeatherByCity(city, "cd9d20a19ea34a019e1230657241112") // Make API call
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body) // Return successful result
                } else {
                    Result.Error("No data available") // Handle case where body is null
                }
            } else {
                Result.Error("Error: ${response.code()} - ${response.message()}") // Handle API error response
            }
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}") // Handle exceptions during the API call
        }
    }

    suspend fun searchCities(query: String): Result<List<City>> {
        return try {
            val response = weatherApi.searchCities("cd9d20a19ea34a019e1230657241112", query)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body) // Return successful result
                } else {
                    Result.Error("No data available") // Handle case where body is null
                }
            } else {
                Result.Error("Error: ${response.code()} - ${response.message()}") // Handle API error response
            }
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}") // Handle exceptions during the API call
        }
    }
}
