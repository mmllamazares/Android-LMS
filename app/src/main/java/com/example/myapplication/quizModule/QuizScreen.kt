package com.example.myapplication.quizModule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun QuizScreen(navController: NavController, quizVM: QuizViewModel) {

// first UI ==> Alert
    if (quizVM.quizFinished) {
//        quizVM.finishQuiz()
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Quiz Finished") },
            text = { Text("Your Score is ${quizVM.score} / ${quizVM.totalQuestions}") },
            confirmButton = {
                Button(onClick = {

                    navController.popBackStack()
                }) {
                    Text("OK")
                }

            })
    } else {

        // second UI ==> Quiz UI
        LaunchedEffect(quizVM.currentQuestion) {
            quizVM.startTimer()
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Time left ${quizVM.timerValue}s",
                style = MaterialTheme.typography.bodyLarge
            )
            LinearProgressIndicator(
                progress = quizVM.timerValue / 10f,
                modifier = Modifier.fillMaxWidth().height(8.dp)
            )
            Text(quizVM.currentQuestion.text, style = MaterialTheme.typography.bodyLarge)
            Row() {
                Button(onClick = {
                    quizVM.answerQuestion(true)
                }) {
                    Text("True")
                }
                Button(onClick = {
                    quizVM.answerQuestion(false)
                }) {
                    Text("False")
                }
            }
        }

    }

}