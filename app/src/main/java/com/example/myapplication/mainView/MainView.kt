package com.example.myapplication.mainView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// colores
private val PrimaryBlue = Color(0xFF1A3ADB)
private val LightBlue = Color(0xFFEEF1FF)
private val AccentBlue = Color(0xFF4A6CF7)
private val TextPrimary = Color(0xFF0D0D2B)
private val TextSecondary = Color(0xFF6B7280)
private val BackgroundGray = Color(0xFFF5F6FA)
private val CardWhite = Color(0xFFFFFFFF)
private val GreenProgress = Color(0xFF10B981)
private val TagBackground = Color(0xFFF0F0F5)

@Composable
fun MainView() {

    var textFieldState by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
            .background(Color.LightGray)
    ) {
        Column() {
            Header()
            Spacer(modifier = Modifier.padding(5.dp))
//            SearchTextField(value = textFieldState, onValueChange = { textFieldState = it })
            SearchBar()
            Spacer(modifier = Modifier.padding(5.dp))
            TitleSection()

        }
    }
}

@Composable
fun Header(headerText: String = "EstomatoLearn") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
//            .padding(top=30.dp)
            .background(Color.White)
    ) {
        Text(
            text = "$headerText",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp, start = 16.dp)
        )
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        TextField(
            value = value, onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Buscar") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
        )
    }
}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        placeholder = {
            Text(
                "Buscar temas",
                color = TextSecondary,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(Icons.Outlined.Search, contentDescription = null, tint = TextSecondary)
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = CardWhite,
            focusedContainerColor = CardWhite,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = AccentBlue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
    )
}

@Composable
fun TitleSection(
    titleText: String = "Operatoria Técnica",
    titleDesc: String = "Especialidad de odontología restauradora"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column() {
            Text(text = titleText, style = MaterialTheme.typography.headlineMedium)
            Text(text = titleDesc, style = MaterialTheme.typography.titleMedium)

        }
    }
}