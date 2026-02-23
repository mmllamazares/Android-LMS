package com.example.myapplication.userName

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun UserNameView(
    viewModel: UserNameViewModel,
    onBack: (() -> Unit)? = null
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UserName(Modifier.align(Alignment.Center), viewModel)

    }
}

@Composable
fun UserName(modifier: Modifier, viewModel: UserNameViewModel) {
    var textFieldState by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Observa el nombre guardado en la base de datos
    val savedUser by viewModel.userName.observeAsState()

    Column(modifier = Modifier) {
        UserNameImage(modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        if (savedUser != null) {
            Text(
                text = "Nombre guardado en BD: ${savedUser!!.name}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        } else {
            Text(
                text = "Aún no hay nombre guardado en BD",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        UserNameTextField(value = textFieldState, onValueChange = { textFieldState = it })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                if (savedUser != null) {
                    viewModel.updateUserName(textFieldState)
                } else {
                    viewModel.setUserName(textFieldState)
                }
                Toast.makeText(context, "Guardado: $textFieldState!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            // El texto del botón cambia según si ya hay usuario o no
            Text(text = if (savedUser != null) "Actualizar nombre" else "Continuar")
        }
//        Button(
//            onClick = {
//                viewModel.setUserName(textFieldState)
//                Toast.makeText(context, "Hola, $textFieldState!", Toast.LENGTH_SHORT).show()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Continuar")
//        }
    }

}

@Composable
fun UserNameTextField(value: String, onValueChange: (String) -> Unit) {

    var textFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1
    )

}

@Composable
fun UserNameImage(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.molly1), contentDescription = "Molly dentista")

}