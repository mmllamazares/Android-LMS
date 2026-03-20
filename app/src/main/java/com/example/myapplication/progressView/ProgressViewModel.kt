package com.example.myapplication.progressView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.myapplication.userName.AppDatabase
import com.example.myapplication.userName.UserRepository

class ProgressViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val quizScore: LiveData<Int>
    val quizTotalQuestions: LiveData<Int>
    val quizCompletedDate: LiveData<String>
    val userName: LiveData<String>

    init {
        val dao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(dao)
        quizScore = repository.quizScore
        quizTotalQuestions = repository.quizTotalQuestions
        quizCompletedDate = repository.quizCompletedDate
        userName = repository.user.map { it?.name ?: "" }
    }
}
