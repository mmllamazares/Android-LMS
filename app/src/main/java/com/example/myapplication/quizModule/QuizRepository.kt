package com.example.myapplication.quizModule

class QuizRepository {
    // get the questions from the web, or from db, cloud database
    private val questions = listOf(
        Question("En dientes anteriores, la entrada a la cavidad endodóntica se realiza por la cara vestibular o bucal.", false),
        Question("Durante el acceso cameral, la cavidad debe corresponder en su forma a la forma de la cámara pulpar.", true),
        Question("En molares mandibulares, el triángulo de acceso cameral tiene su base hacia distal y su vértice hacia mesial.", false),
        Question("La instrumentación de los conductos radiculares debe llegar hasta la constricción apical, que en la mayoría de los casos coincide con el límite cemento-dentinario.", true),
        Question("En dientes jóvenes con pulpa grande, el acceso cameral en incisivos tiene una forma ovoide, mientras que en dientes adultos es triangular.", false)
    )

    fun getAllQuestions():List<Question> = questions

}