package com.example.myapplication.userName

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): LiveData<UserEntity>

    @Query("UPDATE users SET quizScore = :score, quizTotalQuestions = :total, quizCompletedDate = :date WHERE id = :userId")
    suspend fun updateQuizResult(userId: Int, score: Int, total: Int, date: String)

    @Query("SELECT quizScore FROM users LIMIT 1")
    fun getQuizScore(): LiveData<Int>

    @Query("SELECT quizTotalQuestions FROM users LIMIT 1")
    fun getQuizTotalQuestions(): LiveData<Int>

    @Query("SELECT quizCompletedDate FROM users LIMIT 1")
    fun getQuizCompletedDate(): LiveData<String>

}