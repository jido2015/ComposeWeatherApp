package com.example.composeweatherapp.ui.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeweatherapp.domain.model.Weather
import com.example.composeweatherapp.domain.usecase.FetchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.composeweatherapp.domain.model.City
import com.example.composeweatherapp.domain.model.Result

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val appContext: Context // Inject Application context
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Weather>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    // State for city suggestions
    private val _citySuggestions = MutableStateFlow<List<City>>(emptyList())


    // Search cities based on query
    fun searchCities(query: String) {
        // Call the repository to get suggestions
        viewModelScope.launch {
            try {
                when (val suggestions = fetchWeatherUseCase.searchCities(query)) {
                    is Result.Success -> {

                        getWeather(suggestions.data[0].name) // Fetch weather getWeather
                    }
                    is Result.Error -> {
                        _citySuggestions.value = emptyList() // Update UI state with error message
                    }
                    Result.Loading -> {
                        // Loading state is already handled
                    }

                    Result.Empty -> TODO()
                }
            } catch (e: Exception) {
                _citySuggestions.value = emptyList() // Handle error scenario
            }
        }
    }

    // Load the last searched city when the ViewModel is created
    init {
        loadLastSearchedCity()?.let { city ->
            getWeather(city)
        }
    }


    // Function to get weather data by city name
    fun getWeather(city: String) {
        viewModelScope.launch {
            setLoadingState() // Set loading state before the API call
            val result = fetchWeatherUseCase.fetchWeatherByCity(city) // Fetch weather data by city
            handleResult(result)
            saveLastSearchedCity(city)
        }
    }

    // Set loading state
    private fun setLoadingState() {
        _uiState.value = Result.Loading
    }

    // Handle the result and update UI state
    private fun handleResult(result: Result<Weather>) {
        when (result) {
            is Result.Success -> {
                _uiState.value = Result.Success(result.data) // Update UI state with success data
            }
            is Result.Error -> {
                sharedPreferences.edit().clear().apply() // Remove last searched city
                _uiState.value = Result.Error(result.message) // Update UI state with error message
                Log.e("WeatherViewModel", "Error: ${result.message}") // Log error for debugging
            }
            Result.Loading -> {
                // Loading state is already handled
            }

            Result.Empty -> {
                _uiState.value = Result.Empty
            }
        }
    }

    // Save the last searched city to SharedPreferences
    private fun saveLastSearchedCity(city: String) {
        sharedPreferences.edit().putString("last_searched_city", city).apply()
    }

    // Load the last searched city from SharedPreferences
    fun loadLastSearchedCity(): String? {
        return sharedPreferences.getString("last_searched_city", null)
    }
}
