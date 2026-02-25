package com.example.myapplication.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.mainView.MainView
import com.example.myapplication.mainView.MainViewTest
import com.example.myapplication.userName.UserNameView
import com.example.myapplication.userName.UserNameViewModel
import kotlin.getValue


//private val viewModel: UserNameViewModel by viewModels()

@Composable
fun AppNavigation(viewModel: UserNameViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route= AppScreens.MainScreen.route) { MainViewTest(navController)}
        composable(route = AppScreens.UserView.route) { UserNameView(viewModel) }
    }
}