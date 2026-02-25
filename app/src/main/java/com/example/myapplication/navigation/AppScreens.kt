package com.example.myapplication.navigation

sealed class AppScreens(val route:String) {
    object MainScreen: AppScreens("main_screen")
    object UserView: AppScreens("user_view")
    object ProgressView: AppScreens("progress_view")

}