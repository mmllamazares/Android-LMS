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
    private val userRepository: UserRepository
) : ViewModel() {

    // ← var en lugar de val para poder reshuffear en resetQuiz()
    private var questions = repository.getAllQuestions().shuffled().take(5)

    var currentIndex by mutableStateOf(0)
        private set

    var currentQuestion by mutableStateOf(questions[0])
        private set

    var timerValue by mutableStateOf(20)
        private set

    var score by mutableStateOf(0)
        private set

    var quizFinished by mutableStateOf(false)
        private set

    var lastAnswerCorrect by mutableStateOf<Boolean?>(null)
        private set

    private var timerJob: Job? = null

    val totalQuestions: Int get() = questions.size      // siempre 5, automático
    val incorrectCount: Int get() = currentIndex - score

    fun startTimer() {
        timerJob = viewModelScope.launch {
            while (timerValue > 0) {
                delay(1000)
                timerValue--
            }
            finishQuiz()
        }
    }

    fun answerQuestion(userAnswer: Boolean) {
        timerJob?.cancel()
        val isCorrect = userAnswer == currentQuestion.answer
        lastAnswerCorrect = isCorrect
        if (isCorrect) score++
        currentIndex++

        viewModelScope.launch {
            delay(800)
            lastAnswerCorrect = null
            if (currentIndex < questions.size) {
                startNewQuestion()
            } else {
                finishQuiz()
            }
        }
    }

    fun resetQuiz() {
        questions = repository.getAllQuestions().shuffled().take(5)  // ← nuevo set aleatorio
        currentIndex = 0
        score = 0
        quizFinished = false
        lastAnswerCorrect = null
        startNewQuestion()
    }

    fun finishQuiz() {
        timerJob?.cancel()
        quizFinished = true
        viewModelScope.launch {
            try {
                userRepository.saveQuizResult(
                    userId         = 1,
                    score          = score,
                    totalQuestions = totalQuestions
                )
            } catch (e: Exception) {
                println("Error saving quiz result: ${e.message}")
            }
        }
    }

    fun startNewQuestion() {
        timerJob?.cancel()
        timerValue = 20
        if (currentIndex < questions.size) {
            currentQuestion = questions[currentIndex]
        } else {
            quizFinished = true
        }
    }
}