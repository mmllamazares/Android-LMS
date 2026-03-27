package com.example.myapplication.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.mainView.CourseDetailView
import com.example.myapplication.mainView.Courses
import com.example.myapplication.mainView.MainView
//import com.example.myapplication.mainView.MainViewTest
import com.example.myapplication.progressView.ProgressView
import com.example.myapplication.progressView.ProgressViewModel
import com.example.myapplication.quizModule.QuizScreen
import com.example.myapplication.quizModule.QuizViewModel
import com.example.myapplication.userName.UserNameView
import com.example.myapplication.userName.UserNameViewModel
import kotlinx.coroutines.delay
import kotlin.getValue


//private val viewModel: UserNameViewModel by viewModels()


@Composable
fun AppNavigation(
    viewModel: UserNameViewModel,
    quizVM: QuizViewModel,
    progressVM: ProgressViewModel = viewModel()
) {
    val navController = rememberNavController()

    // Observar el estado del usuario
    val savedUser by viewModel.userName.observeAsState()

    // Estado para controlar la pantalla de splash
    var showSplash by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        delay(2000)  // Siempre mostrar splash por 2 segundos al inicio
        showSplash = false
    }

    if (showSplash) {
        // Pantalla de Splash
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()  // Indicador de carga
                Text("Cargando...", modifier = Modifier.padding(top = 16.dp))
            }
        }
    } else {
        // Determinar el destino inicial basado en si hay usuario
        val startDestination = if (savedUser == null) {
            AppScreens.UserView.route  // Si no hay usuario, ir a UserNameView
        } else {
            AppScreens.MainScreen.route  // Si hay usuario, ir a MainView
        }

        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = AppScreens.MainScreen.route) { MainView(navController) }
            composable(
                route = AppScreens.CourseDetailView.route + "/{index}",
                arguments = listOf(navArgument("index") {
                    type = NavType.IntType
                })
            ) { entry ->
                CourseDetailView(
                    courseItem = Courses.dummyCourses[entry.arguments!!.getInt("index")],
                    navController = navController
                )
            }
            composable(route = AppScreens.UserView.route) { UserNameView(viewModel) }
            composable(route = AppScreens.UserViewBack.route) { UserNameView(viewModel, onBack = { navController.popBackStack() }) }
            composable(route = AppScreens.ProgressView.route) {
                ProgressView(
                    navController = navController,
                    viewModel = progressVM
                )
            }
            composable(route = AppScreens.QuizView.route) {
                QuizScreen(
                    navController = navController,
                    quizVM = quizVM
                )
            }
        }
    }
}