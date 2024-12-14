package com.example.composeweatherapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeweatherapp.R
import com.example.composeweatherapp.domain.model.Condition
import com.example.composeweatherapp.domain.model.Current
import com.example.composeweatherapp.domain.model.Location
import com.example.composeweatherapp.domain.model.Weather
import com.example.composeweatherapp.ui.ui_components.extention.outlineColors
import com.example.composeweatherapp.domain.model.Result
import com.example.composeweatherapp.ui.theme.lightGrey

@Composable
fun SearchAndCitySuggestions(
    query: String,
    uiState: Result<Weather>, // List of city suggestions
    onQueryChanged: (String) -> Unit, // Function to handle query change
    onCitySelected: (Weather) -> Unit, // Function to handle city selection
    modifier: Modifier = Modifier
) {

    Column(modifier = Modifier.padding(16.dp)) {
        // Search Bar
        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),
            value = query,
            colors = outlineColors(),
            onValueChange = { onQueryChanged(it) },
            label = { Text("Search Location") },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true,
            trailingIcon = { // Place the icon on the right
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        )

        if (query.isNotEmpty()) {
            when (uiState) {
                is Result.Success -> {
                    val weather = uiState.data
                    // Display City Suggestions
                    LazyColumn {
                        items(listOf(weather)) { city ->
                            CitySuggestionItem(weather = city, onCitySelected = onCitySelected, onQueryChanged)
                        }
                    }
                }
                is Result.Error -> {
                    Text(
                        text = "No cities found",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                is Result.Loading -> {
                    // Loading state
                }
                Result.Empty -> {
                    // Empty state
                }
            }
        }
    }
}

@Composable
fun CitySuggestionItem( weather: Weather, onCitySelected: (Weather) -> Unit,     onQueryChanged: (String) -> Unit // Function to handle query change
) {

        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .clickable {
                    onCitySelected(weather)
                    onQueryChanged("")
                    Log.d("CitySuggestionItem", weather.location.name)

                }

        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(lightGrey)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically){

                    Column(
                        modifier = Modifier
                            .padding(16.dp) // Padding inside the card
                    ) {
                        Text(
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                            text = weather.location.name
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            style = TextStyle(fontSize = 60.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                            text = "${weather.current.temp_c}°"
                        )
                    }
                    AsyncImage(
                        model = "https://${weather.current.condition.icon}",
                        modifier = Modifier.size(123.dp),
                        contentDescription = "Weather icon",
                        placeholder = painterResource(id = R.drawable.clear))
                }

            }

        }
}


@Composable
@Preview(showBackground = true)
fun CitySuggestionItemPreview() {
    val mockWeather = Weather(
        current = Current(
            temp_c = 25.0,
            condition = Condition(
                text = "Sunny",
                icon = "icons.veryicon.com/png/o/miscellaneous/logo-design-of-lingzhuyun/icon-correct-24-1.png", // Sample drawable for the preview
                code = 4 // Replace with a valid mock icon URL
            ),
            humidity = 4.0,
            uv = 5.5,
            feelslike_c = 44.0
        ),
        location = Location(
            name = "Sample City",
            region = "Sample Region",
            country = "Sample Country"
        )
    )

    CitySuggestionItem(
        weather = mockWeather,
        onCitySelected = { /* Handle selection in preview */ },
        onQueryChanged = { }
    )
}
