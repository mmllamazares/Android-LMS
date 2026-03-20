package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.mainView.MainView
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.quizModule.QuizViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.userName.UserNameView
import com.example.myapplication.userName.UserNameViewModel


// Todas las pantallas posibles de la app
//enum class AppScreen {
//    UserName,
//    Courses,
//    Profile
//}
class MainActivity : ComponentActivity() {
    private val viewModel: UserNameViewModel by viewModels()
    private val quizVM: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {

                val savedUser by viewModel.userName.observeAsState()

                AppNavigation(viewModel, quizVM)

//                // Pantalla actual — arranca en Courses si hay usuario, si no en UserName
//                var currentScreen by remember(savedUser) {
//                    mutableStateOf(
//                        if (savedUser != null) AppScreen.Courses else AppScreen.UserName
//                    )
//                }
//
//                // Intercepta el botón físico de atrás
//                // Solo actúa cuando estás en Profile, regresa a Courses
//                BackHandler(enabled = currentScreen == AppScreen.Profile) {
//                    currentScreen = AppScreen.Courses
//                }
//
//                when (currentScreen) {
//                    AppScreen.UserName -> UserNameView(
//                        viewModel = viewModel,
//                        onBack = if (savedUser != null) {
//                            // Si ya tiene usuario, puede volver atrás (viene desde Perfil)
//                            { currentScreen = AppScreen.Courses }
//                        } else {
//                            // Primera vez — no hay botón atrás
//                            null
//                        }
//                    )
//
//                    AppScreen.Courses -> MainView(
//                        viewModel = viewModel,
//                        onNavigateToProfile = { currentScreen = AppScreen.Profile }
//                    )
//
//                    AppScreen.Profile -> UserNameView(
//                        viewModel = viewModel,
//                        onBack = { currentScreen = AppScreen.Courses }
//                    )
//                }


//                // Observa si existe un usuario guardado en la BD
//                val savedUser by viewModel.userName.observeAsState()
//
//                when {
//                    // Todavía Room no respondió — no renderiza nada para evitar un flash
//                    savedUser == null -> UserNameView(viewModel)
//
//                    // Ya hay usuario registrado → va directo a los cursos
//                    else -> MainView()
//                }
//                MainView()
//                UserNameView(viewModel)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(
////                        name = "Android",
////                        modifier = Modifier.padding(innerPadding)
////                    )
//
//                }
            }
        }
    }
}