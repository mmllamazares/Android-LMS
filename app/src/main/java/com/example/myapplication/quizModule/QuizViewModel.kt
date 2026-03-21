package com.example.myapplication.quizModule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.userName.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(
    private val repository: QuizRepository = QuizRepository(),
    private val userRepository: UserRepository  // ← AGREGAR
) :
    ViewModel() {
    private val questions = repository.getAllQuestions()

    var currentIndex by mutableStateOf(0)
        private set

    var currentQuestion by mutableStateOf(questions[0])
        private set

    var timerValue by mutableStateOf(10)
        private set

    var score by mutableStateOf(0)
        private set

    var quizFinished by mutableStateOf(false)
        private set

    private var timerJob: Job? = null

    val totalQuestions: Int
        get() = questions.size

    fun startTimer() {
        timerJob = viewModelScope.launch {
            while (timerValue > 0) {
                delay(1000)
                timerValue--
            }
            // timer == 0
            finishQuiz()
        }
    }

    fun answerQuestion(userAnswer: Boolean) {
        timerJob?.cancel()
        if (userAnswer == currentQuestion.answer) {
            score++
        }
        currentIndex++
        if (currentIndex < questions.size) {
            startNewQuestion()
        } else {
            finishQuiz()
        }
    }

    fun resetQuiz() {
        currentIndex = 0
        score = 0
        quizFinished = false
        startNewQuestion()
    }

    fun finishQuiz() {
        timerJob?.cancel()
        quizFinished = true
        // Guardar resultado aquí cuando finalice ↓

        viewModelScope.launch {
            try {
                // Obtener el usuario de forma síncrona en la corrutina
                userRepository.saveQuizResult(
                    userId = 1,  // O mejor aún, obtén el ID del usuario guardado
                    score = score,
                    totalQuestions = totalQuestions
                )
            } catch (e: Exception) {
                // Log para debugging
                println("Error saving quiz result: ${e.message}")
            }
        }

//        viewModelScope.launch {
//            // Obtener el usuario del repository de forma correcta
//            val currentUser = userRepository.user.value
//            if (currentUser != null) {
//                userRepository.saveQuizResult(
//                    userId = currentUser.id,
//                    score = score,
//                    totalQuestions = totalQuestions
//                )
//            }
//        }


//        viewModelScope.launch {
//            val userId = userRepository.user.value?.id
//            if (userId != null) {
//                userRepository.saveQuizResult(
//                    userId = userId,  // ← Usar el ID dinámico
//                    score = score,
//                    totalQuestions = totalQuestions
//                )
//            } else {
//                // Manejar caso donde no hay usuario (ej. log o no guardar)
//                // Log.e("QuizViewModel", "No user found to save quiz result")
//            }
//        }


        //        viewModelScope.launch {
//            userRepository.saveQuizResult(
//                userId = 1,  // Obtener ID del usuario actual
//                score = score,
//                totalQuestions = totalQuestions
//            )
//        }
    }

//    fun finishQuiz() {
//        timerJob?.cancel()
//        quizFinished = true
//    }

    fun startNewQuestion() {
        timerJob?.cancel()
        timerValue = 10
        if (currentIndex < questions.size) {
            currentQuestion = questions[currentIndex]
        } else {
            quizFinished = true
        }
    }

}