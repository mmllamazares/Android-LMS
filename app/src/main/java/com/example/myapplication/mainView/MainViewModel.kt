package com.example.myapplication.mainView

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

class MainViewModel: ViewModel(){
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _courses = MutableStateFlow(Courses.dummyCourses)
    val courses = searchText.combine(_courses) {
        text, courses ->
        if(text.isBlank()){
            courses
        }else{
            courses.filter { it.title.contains(text) }
        }
    }
}