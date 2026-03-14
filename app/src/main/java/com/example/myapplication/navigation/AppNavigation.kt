package com.example.myapplication.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
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
import com.example.myapplication.userName.UserNameView
import com.example.myapplication.userName.UserNameViewModel
import kotlin.getValue


//private val viewModel: UserNameViewModel by viewModels()

@Composable
fun AppNavigation(viewModel: UserNameViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) { MainView(navController) }
        composable(
            route = AppScreens.CourseDetailView.route + "/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {entry->
            CourseDetailView(
                courseItem = Courses.dummyCourses[entry.arguments!!.getInt("index")],
                navController = navController
            )
        }
        composable(route = AppScreens.UserView.route) { UserNameView(viewModel) }
        composable(route = AppScreens.ProgressView.route) { ProgressView(navController = navController) }
    }
}