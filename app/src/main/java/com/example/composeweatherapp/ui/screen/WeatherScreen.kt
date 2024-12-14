package com.example.composeweatherapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeweatherapp.ui.extension.getWeather
import com.example.composeweatherapp.ui.ui_components.NoCitySelectedMessage
import com.example.composeweatherapp.ui.ui_components.ShowWeather
import com.example.composeweatherapp.ui.viewmodel.WeatherViewModel
import com.example.composeweatherapp.domain.model.Weather
import com.example.composeweatherapp.domain.model.Result

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    var city by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    var query by remember  { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf<Weather?>(null) }

    // Fetch city suggestions when query changes
    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            viewModel.searchCities(query) // Trigger search for city suggestions
        }
    }

    // Load the last searched city into the TextField
    LaunchedEffect(uiState) {
        if (uiState is Result.Success) {
            city = viewModel.loadLastSearchedCity() ?: ""
        }
    }

    val scrollState = rememberScrollState()


    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White)){
        Column (modifier = Modifier.fillMaxSize().padding(top = 44.dp, start = 20.dp, end = 20.dp), // Set the background color

            horizontalAlignment = Alignment.CenterHorizontally,) {
            // SearchAndCitySuggestions stays at the top
            SearchAndCitySuggestions(
                query = query,
                uiState = uiState,
                onQueryChanged = { updatedQuery ->
                    query = updatedQuery // Update query state
                },
                onCitySelected = { citySelected ->
                    selectedCity = citySelected // Update selected city
                },
            )

            // Column stays at the bottom, scrollable if needed
            // If no city is selected, show a message, otherwise, show the weather displays

            val weather = getWeather(uiState)
            if (weather != null) {
                ShowWeather(weather, scrollState, isCitySelected = query.isNotEmpty())
            } else {

                if ( query.isEmpty()) {
                    Log.d("NoCitySelectedMessage", "${selectedCity}, $query")
                    NoCitySelectedMessage()
                }
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
fun WeatherScreenPreview() {
    WeatherScreen() // Pass mock uiState directly
}

