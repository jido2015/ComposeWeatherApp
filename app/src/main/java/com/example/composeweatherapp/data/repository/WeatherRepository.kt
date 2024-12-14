package com.example.composeweatherapp.data.repository

import com.example.composeweatherapp.data.api.WeatherApiService
import com.example.composeweatherapp.domain.model.City
import com.example.composeweatherapp.domain.model.Weather
import javax.inject.Inject
import com.example.composeweatherapp.domain.model.Result

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
                    Result.Success(body)
                } else {
                    Result.Error("No data available")
                }
            } else {
                Result.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    suspend fun searchCities(query: String): Result<List<City>> {
        return try {
            val response = weatherApi.searchCities("cd9d20a19ea34a019e1230657241112", query)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error("No data available")
                }
            } else {
                Result.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }
}
