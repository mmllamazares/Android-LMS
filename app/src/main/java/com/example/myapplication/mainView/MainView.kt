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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
            SearchTextField(value = textFieldState, onValueChange = { textFieldState = it })
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