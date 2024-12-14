package com.example.composeweatherapp

import com.example.composeweatherapp.data.repository.WeatherRepository
import com.example.composeweatherapp.domain.model.Condition
import com.example.composeweatherapp.domain.model.Current
import com.example.composeweatherapp.domain.model.Location
import com.example.composeweatherapp.domain.model.Weather
import com.example.composeweatherapp.domain.usecase.FetchWeatherUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import com.example.composeweatherapp.domain.model.Result


@ExperimentalCoroutinesApi
class FetchWeatherUseCaseTest {

    private lateinit var fetchWeatherUseCase: FetchWeatherUseCase
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mock()
        fetchWeatherUseCase = FetchWeatherUseCase(repository)
    }


    @Test
    fun `fetchWeather returns data from repository`()  {
        runTest {
            // Given
            val weatherResponse = Weather(
                Location("Clear", "", ""),
                Current(2.0,
                    Condition("", "", 0),0.0, 0.0, 0.0)
            )
            `when`(repository.fetchWeather("New York")).thenReturn(Result.Success(weatherResponse))

            // When
            val result = fetchWeatherUseCase.fetchWeatherByCity("New York")

            // Then
            assertEquals(weatherResponse, (result as Result.Success).data)
        }
    }

    @Test
    fun `fetchWeather returns error for unknown city`() {
        runTest {
            // Given
            `when`(repository.fetchWeather("Unknown City")).thenReturn(Result.Error("No data available"))

            // When
            val result = fetchWeatherUseCase.fetchWeatherByCity("New York")

            // Then
            assertTrue(result is Result.Error)
            assertEquals("No data available", (result as Result.Error).message)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Main dispatcher to the original
    }

}
