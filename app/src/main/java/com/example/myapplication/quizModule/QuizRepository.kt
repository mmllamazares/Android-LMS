package com.example.myapplication.quizModule

class QuizRepository {
    // get the questions from the web, or from db, cloud database
    private val questions = listOf(
        // Preguntas originales
        Question("En dientes anteriores, la entrada a la cavidad endodóntica se realiza por la cara vestibular o bucal.", false),
        Question("Durante el acceso cameral, la cavidad debe corresponder en su forma a la forma de la cámara pulpar.", true),
        Question("En molares mandibulares, el triángulo de acceso cameral tiene su base hacia distal y su vértice hacia mesial.", false),
        Question("La instrumentación de los conductos radiculares debe llegar hasta la constricción apical, que en la mayoría de los casos coincide con el límite cemento-dentinario.", true),
        Question("En dientes jóvenes con pulpa grande, el acceso cameral en incisivos tiene una forma ovoide, mientras que en dientes adultos es triangular.", false),

        // Preguntas nuevas
        Question("En dientes posteriores, el acceso cameral se realiza siempre a través de la superficie oclusal.", true),
        Question("En dientes multiradiculares, es aceptable deformar el piso de la cámara pulpar durante el acceso.", false),
        Question("El límite de la apertura coronaria debe incluir todos los cuernos pulpares.", true),
        Question("Es correcto localizar el conducto a través de la cavidad de caries durante el acceso cameral.", false),
        Question("La pulpa cameral es mayor en un diente adulto que en un diente joven.", false),
        Question("El acceso cameral en premolares superiores e inferiores tiene un contorno ovoide.", true),
        Question("En premolares inferiores, no es necesario incluir la cúspide vestibular en la apertura cameral.", false),
        Question("La penetración inicial en dientes anteriores se realiza en el tercio medio-lingual, entre 2 y 3 mm del borde incisal.", true),
        Question("La fresa utilizada para la penetración inicial en el acceso cameral de dientes anteriores es la fresa redonda #4.", false),
        Question("La sensación de vacío o caída durante la preparación indica que la fresa ha llegado a la cámara pulpar.", true),
        Question("En incisivos, la forma del acceso cameral es triangular.", true),
        Question("En el canino, la forma del acceso cameral es cuadrada.", false),
        Question("En molares maxilares, la penetración inicial se dirige hacia el cuerno mesio-bucal, próximo a la cúspide homónima.", true),
        Question("En el primer molar superior, el contorno de acceso es triangular en todos los casos, sin modificaciones.", false),
        Question("El desgaste compensatorio forma parte de los pasos de la técnica de acceso cameral.", true),
        Question("En molares maxilares, la entrada se realiza en los dos tercios distales de la superficie oclusal.", false),
        Question("La tríada endodóntica está conformada por asepsia, preparación biomecánica y sellado hermético.", true),
        Question("La instrumentación de los conductos busca únicamente dar forma al conducto, sin importar la eliminación de bacterias.", false),
        Question("En el acceso cameral de molares mandibulares, la penetración inicial se realiza entre el vértice de la cúspide mesio-vestibular y la estría central.", true),
        Question("La fresa redonda #4 se utiliza para eliminar los restos del techo de la cámara pulpar una vez localizada la entrada.", true),
        Question("Nunca debe deformarse la pared cervical ni el piso de la cámara pulpar durante el acceso cameral.", true)
    )

    fun getAllQuestions(): List<Question> = questions

}