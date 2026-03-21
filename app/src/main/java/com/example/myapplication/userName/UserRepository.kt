package com.example.myapplication.userName

import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.text.format

// UserRepository.kt
class UserRepository(private val userDao: UserDao) {
    val user: LiveData<UserEntity> = userDao.getUser()
    val quizScore: LiveData<Int> = userDao.getQuizScore()
    val quizTotalQuestions: LiveData<Int> = userDao.getQuizTotalQuestions()
    val quizCompletedDate: LiveData<String> = userDao.getQuizCompletedDate()

    suspend fun saveUser(name: String) {
        userDao.insertUser(UserEntity(name = name))
    }

    suspend fun updateUser(name: String, id: Int) {
        userDao.updateUser(UserEntity(id = id, name = name))
    }

    suspend fun saveQuizResult(userId: Int, score: Int, totalQuestions: Int) {
        val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        val currentUser = userDao.getUserSync(userId) // ← Usar la nueva query

        if (currentUser != null) {
            // Crear entidad actualizada manteniendo todos los campos
            val updatedUser = UserEntity(
                id = currentUser.id,
                name = currentUser.name,
                quizScore = score,
                quizTotalQuestions = totalQuestions,
                quizCompletedDate = currentDate
            )
            userDao.updateQuizResult(updatedUser)
        }
//
    }
}