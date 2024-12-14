package com.example.composeweatherapp.ui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import com.example.composeweatherapp.ui.theme.lightGrey


@Composable
fun ShowWeather(weather: Weather, scrollState: ScrollState, isCitySelected: Boolean) {

    if (!isCitySelected){
        Column(
            modifier = Modifier.padding(top = 80.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState), // Make the column scrollable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = "https://${weather.current.condition.icon}",
                modifier = Modifier.size(123.dp),
                contentDescription = "Weather icon",
                placeholder = painterResource(id = R.drawable.clear)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    text = weather.location.name
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.arrrow),
                    contentDescription = "Weather icon",
                )
            }

            Text(
                style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                text = " ${weather.current.temp_c}°"
            )

            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(top = 35.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(lightGrey),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp), // Padding inside the card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                            text = "Humidity"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
                            text = "${weather.current.humidity}%"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp), // Padding inside the card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                            text = "UV"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
                            text = weather.current.uv.toString()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp), // Padding inside the card
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                            text = "Feels Like"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
                            text = weather.current.feelslike_c.toString()
                        )
                    }
                }

            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ShowWeatherPreview() {
    // Mocking weather data
    val mockWeather = Weather(
        current = Current(
            temp_c = 25.0,
            condition = Condition(
                text = "Sunny",
                icon = "10d",
                code = 1000 // Example condition code
            ),
            humidity = 50.0, // Example humidity value
            uv = 5.0,      // Example UV index
            feelslike_c = 27.0 // Example feels like temperature
        ),
        location = Location(
            name = "Baltimore",
            region = "Maryland",
            country = "USA"
        )
    )

    ShowWeather(
        weather = mockWeather,
        scrollState = rememberScrollState(),
        isCitySelected = false // Mocking city selected
    )
}

