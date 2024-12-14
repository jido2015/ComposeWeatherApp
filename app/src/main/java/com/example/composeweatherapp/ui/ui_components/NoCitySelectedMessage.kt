package com.example.composeweatherapp.ui.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoCitySelectedMessage() {

        Column(
            modifier = Modifier.padding(top = 240.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No City Selected",
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Please Search For A City",
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                modifier = Modifier.padding(8.dp)
            )
        }
}
