package com.example.myapplication.userName

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
//import com.example.myapplication.mainView.TextPrimary

// ── Colores ───────────────────────────────────────────────────────────────────
private val UBlue = Color(0xFF1A3ADB)
private val UBlueDark = Color(0xFF1530B8)
private val ULightBlue = Color(0xFFEEF1FF)
private val UBg = Color(0xFFF5F6FA)
private val UWhite = Color(0xFFFFFFFF)
private val UTextPrimary = Color(0xFF0D0D2B)
private val UTextSecondary = Color(0xFF6B7280)
private val UBorder = Color(0xFFE5E7EB)
private val UInputBg = Color(0xFFF5F6FA)

// ── Vista principal ───────────────────────────────────────────────────────────
@Composable
fun UserNameView(
    viewModel: UserNameViewModel,
    onBack: (() -> Unit)? = null
) {
    val savedUser by viewModel.userName.observeAsState()
    val isUpdate = savedUser != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UBg)
    ) {
        // Header azul
        UserNameHeader(
            isUpdate = isUpdate,
            currentName = savedUser?.name,
            onBack = onBack
        )

        // Formulario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            UserNameForm(
                viewModel = viewModel,
                isUpdate = isUpdate,
                savedName = savedUser?.name,
                onBack = onBack
            )
        }
    }
}

// ── Header azul ───────────────────────────────────────────────────────────────
@Composable
fun UserNameHeader(
    isUpdate: Boolean,
    currentName: String?,
    onBack: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(UBlue)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón volver (solo si hay onBack)
        if (onBack != null) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp, start = 8.dp)) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = UWhite
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(48.dp))
        }

        // Ícono de la app
        Box(
            modifier = Modifier
                .size(68.dp)
                .clip(CircleShape)
                .background(UWhite.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.molly1),
                contentDescription = "EstomatoLearn",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
//            Icon(
//                imageVector        = Icons.Filled.Person,
//                contentDescription = null,
//                tint               = UWhite,
//                modifier           = Modifier.size(36.dp)
//            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "EstomatoLearn",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = UWhite
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = if (isUpdate) "Editar perfil"
            else "¡Bienvenido! ¿Cómo te llamamos?",
            fontSize = 13.sp,
            color = UWhite.copy(alpha = 0.7f)
        )

        // Chip de nombre guardado
        if (isUpdate && currentName != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(UWhite.copy(alpha = 0.15f))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4ADE80))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Nombre actual: ",
                        fontSize = 12.sp,
                        color = UWhite.copy(alpha = 0.75f)
                    )
                    Text(
                        text = currentName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = UWhite
                    )
                }
            }
        }
    }
}

// ── Formulario ────────────────────────────────────────────────────────────────
@Composable
fun UserNameForm(
    viewModel: UserNameViewModel,
    isUpdate: Boolean,
    savedName: String?,
    onBack: (() -> Unit)?
) {
    var textValue by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    val isValid = textValue.trim().length >= 2

    // Card blanca del formulario
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(UWhite)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {

        // Label
        Text(
            text = if (isUpdate) "NUEVO NOMBRE" else "TU NOMBRE",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = UTextSecondary,
            letterSpacing = 0.8.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Input
        OutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it },
            placeholder = {
                Text(
                    text = if (isUpdate) "Escribe tu nuevo nombre…" else "Ej. Dr. García",
                    color = UTextSecondary,
                    fontSize = 14.sp
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboard?.hide() }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = UInputBg,
                focusedContainerColor = UInputBg,
                unfocusedBorderColor = UBorder,
                focusedBorderColor = UBlue,
                cursorColor = UBlue
            ),
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = UTextPrimary)
        )

        // Hint
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Este nombre aparecerá en tus resultados y al compartir tu progreso.",
            fontSize = 12.sp,
            color = UTextSecondary,
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón principal
        Button(
            onClick = {
                if (isValid) {
                    if (isUpdate) {
                        viewModel.updateUserName(textValue.trim())
                    } else {
                        viewModel.setUserName(textValue.trim())
                    }
                    keyboard?.hide()
                    Toast.makeText(
                        context,
                        if (isUpdate) "Nombre actualizado" else "¡Bienvenido, ${textValue.trim()}!",
                        Toast.LENGTH_SHORT
                    ).show()
                    textValue = ""
                }
            },
            enabled = isValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = UBlue,
                contentColor = UWhite,
                disabledContainerColor = UBlue.copy(alpha = 0.35f),
                disabledContentColor = UWhite.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = if (isUpdate) "Actualizar nombre" else "Continuar",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Botón secundario volver (solo en modo edición desde perfil)
        if (isUpdate && onBack != null) {
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Volver sin cambios",
                    fontSize = 14.sp,
                    color = UTextSecondary
                )
            }
        }
    }
}