package com.example.composeweatherapp.ui.ui_components.extention

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composeweatherapp.ui.theme.lightGrey

// Setting input field property to conform with light and dark mode
@Composable
fun outlineColors(): TextFieldColors {
    return  OutlinedTextFieldDefaults.colors(

        focusedContainerColor = lightGrey,
        unfocusedContainerColor = lightGrey,
        disabledContainerColor = lightGrey,
        errorContainerColor = Color.Transparent,
        cursorColor = Color.Black,
        selectionColors = LocalTextSelectionColors.current,
        focusedBorderColor = lightGrey,
        unfocusedBorderColor = lightGrey,
        focusedLabelColor = Color.Black,
    )
}