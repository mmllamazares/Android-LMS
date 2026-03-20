package com.example.myapplication.userName

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quizScore: Int = 0,           // ← AGREGAR: puntuación obtenida
    val quizTotalQuestions: Int = 0,  // ← AGREGAR: total de preguntas
    val quizCompletedDate: String = "" // ← AGREGAR: fecha/hora de finalización
)