package com.example.composeweatherapp.domain.usecase

import com.example.composeweatherapp.data.repository.WeatherRepository
import com.example.composeweatherapp.domain.model.Result
import com.example.composeweatherapp.domain.model.City
import com.example.composeweatherapp.domain.model.Weather
import javax.inject.Inject

// Use case for fetching weather data
class FetchWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend fun fetchWeatherByCity(city: String): Result<Weather> {
        return repository.fetchWeather(city) // Invoke the repository method
    }

    suspend fun searchCities(query: String): Result<List<City>> {
        return repository.searchCities(query) // Invoke the repository method
    }
}