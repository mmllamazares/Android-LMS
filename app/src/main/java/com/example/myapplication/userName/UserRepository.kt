package com.example.myapplication.userName

import androidx.lifecycle.LiveData

// UserRepository.kt
class UserRepository(private val userDao: UserDao) {
    val user: LiveData<UserEntity> = userDao.getUser()

    suspend fun saveUser(name: String) {
        userDao.insertUser(UserEntity(name = name))
    }

    suspend fun updateUser(name: String, id: Int) {
        userDao.updateUser(UserEntity(id = id, name = name))
    }
}