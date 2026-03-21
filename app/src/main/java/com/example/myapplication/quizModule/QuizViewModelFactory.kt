package com.example.myapplication.quizModule



import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.userName.UserRepository

class QuizViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(
            repository = QuizRepository(),
            userRepository = userRepository
        )  as T
    }
}
