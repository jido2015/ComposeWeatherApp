package com.example.composeweatherapp.ui.extension

import com.example.composeweatherapp.domain.model.Weather
import com.example.composeweatherapp.domain.model.Result

fun getWeather(uiState: Result<Weather>): Weather? {
    return when (uiState) {
        is Result.Success-> {
            uiState.data
        }
        is Result.Error -> {
            null
        }
        is Result.Loading -> {
            null
        }
        Result.Empty -> {
            null
        }
    }
}
