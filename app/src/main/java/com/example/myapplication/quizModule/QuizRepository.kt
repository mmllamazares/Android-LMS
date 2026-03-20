package com.example.myapplication.quizModule

class QuizRepository {
    // get the questions from the web, or from db, cloud database
    private val questions = listOf(
        Question("The Great Wall of China is visible from space with the naked eye", false),
        Question("Water boils at 100°C at sea level.", true),
        Question("Humans have more than five senses.", true),
        Question("Lightning never strikes the same place twice.", false)
    )

    fun getAllQuestions():List<Question> = questions

}