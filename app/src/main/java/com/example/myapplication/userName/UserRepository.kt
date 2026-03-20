package com.example.myapplication.userName

import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// UserRepository.kt
class UserRepository(private val userDao: UserDao) {
    val user: LiveData<UserEntity> = userDao.getUser()

    suspend fun saveUser(name: String) {
        userDao.insertUser(UserEntity(name = name))
    }

    suspend fun updateUser(name: String, id: Int) {
        userDao.updateUser(UserEntity(id = id, name = name))
    }

    suspend fun saveQuizResult(userId: Int, score: Int, totalQuestions: Int) {
        val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        userDao.updateQuizResult(userId, score, totalQuestions, currentDate)
    }
}