package com.example.composeweatherapp.domain.model

// Data classes for parsing weather data
data class Weather(

    val location: Location,
    val current: Current
)

data class Location(

    val name: String,
    val region: String,
    val country: String
)

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Double,
    val uv: Double,
    val feelslike_c: Double,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int

)