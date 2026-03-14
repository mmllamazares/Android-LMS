package com.example.myapplication.navigation

sealed class AppScreens(val route:String) {
    object MainScreen: AppScreens("main_screen")
    object CourseDetailView: AppScreens("course_detail_view")

    object UserView: AppScreens("user_view")
    object ProgressView: AppScreens("progress_view")

}