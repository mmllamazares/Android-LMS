package com.example.myapplication.mainView

import androidx.compose.ui.graphics.Color

data class CourseItem(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val progress: Float?,        // null = no iniciado
    val totalLessons: Int?,
    val completedLessons: Int?,
    val duration: String?,
    val modules: Int?,
    val students: Int?,
    val avatarColors: List<Color>?,
    val buttonLabel: String,
    val imageColor: Color
)

object Courses {

    val dummyCourses = listOf(
        CourseItem(
            id = 1,
            title = "Acceso Cameral",
            description = "Introducción al procedimiento quirúrgico de apertura de la cámara pulpar y su\n" +
                    "propósito clínico.",
            content = " Fase quirúrgica con la que se abre la cámara pulpar previa a la localización y\n" +
                    "exploración de los conductos. Su objetivo es obtener un acceso directo, amplio y sin\n" +
                    "obstáculos a la zona apical del conducto radicular, para lo cual es indispensable dominar la\n" +
                    "morfología de la cámara pulpar, así como las posibles alteraciones que puedan presentarse",
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Continuar Aprendiendo",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 2,
            title = "Materiales Dentales",
            description = "Resinas, ionómeros y adhesivos de última generación.",
            content = "ta weno",
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = "4h 30m",
            modules = 8,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver Detalles",
            imageColor = Color(0xFF2D6A4F)
        ),
//        CourseItem(
//            title = "Instrumental Quirúrgico",
//            description = "Reconocimiento y uso de instrumental rotatorio y manual.",
//            progress = null,
//            totalLessons = null,
//            completedLessons = null,
//            duration = null,
//            modules = null,
//            students = 120,
//            avatarColors = listOf(Color(0xFF6366F1), Color(0xFF10B981), Color(0xFFF59E0B)),
//            buttonLabel = "Empezar Curso",
//            imageColor = Color(0xFF1F4068)
//        )
    )
}