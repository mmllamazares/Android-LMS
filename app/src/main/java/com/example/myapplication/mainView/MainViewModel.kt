package com.example.myapplication.mainView

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import java.util.Locale
import java.util.Locale.getDefault

class MainViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _courses = MutableStateFlow(Courses.dummyCourses)

    val courses = searchText
        .debounce(500L)
        .combine(_courses) { text, courses ->
            if (text.isBlank()) {
                courses
            } else {
                courses.filter { it.title.lowercase(getDefault()).contains(text.lowercase(getDefault())) }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _courses.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}