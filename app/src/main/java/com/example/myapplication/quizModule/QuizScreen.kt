package com.example.myapplication.quizModule

import android.R
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.navigation.AppScreens

// ── Colores ───────────────────────────────────────────────────────────────────
private val QuizBlue        = Color(0xFF1A3ADB)
private val QuizBlueDark    = Color(0xFF1530B8)
private val QuizLightBlue   = Color(0xFFEEF1FF)
private val QuizBg          = Color(0xFFF5F6FA)
private val QuizWhite       = Color(0xFFFFFFFF)
private val QuizTextPrimary = Color(0xFF0D0D2B)
private val QuizTextMuted   = Color(0xFF6B7280)
private val QuizGreen       = Color(0xFF10B981)
private val QuizGreenLight  = Color(0xFFF0FDF4)
private val QuizGreenDark   = Color(0xFF065F46)
private val QuizRed         = Color(0xFFEF4444)
private val QuizRedLight    = Color(0xFFFEF2F2)
private val QuizRedDark     = Color(0xFF991B1B)
private val QuizBorder      = Color(0xFFE5E7EB)

// ── Pantalla principal ────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController, quizVM: QuizViewModel) {

    if (quizVM.quizFinished) {
        QuizFinishedScreen(
            score = quizVM.score,
            total = quizVM.totalQuestions,
//            onBack = { navController.popBackStack() },
            onResults = {navController.navigate(route = AppScreens.ProgressView.route)},
            onRetry = { quizVM.resetQuiz() }
        )
        return
    }

    LaunchedEffect(quizVM.currentQuestion) {
        quizVM.startTimer()
    }

    Scaffold(
        topBar = {
            QuizHeader(
                currentIndex = quizVM.currentIndex,
                total        = quizVM.totalQuestions,
                timerValue   = quizVM.timerValue,
                navController = navController
            )
        },
        containerColor = QuizBg
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(4.dp))

            // Tarjeta de pregunta con timer circular
            QuestionCard(
                question    = quizVM.currentQuestion.text,
                timerValue  = quizVM.timerValue,
                maxTimer    = 20
            )

            // Feedback de respuesta anterior
            quizVM.lastAnswerCorrect?.let { correct ->
                FeedbackBanner(correct = correct, correctAnswer = quizVM.currentQuestion.answer)
            }

            // Botones de respuesta — se deshabilitan mientras hay feedback
            val answering = quizVM.lastAnswerCorrect == null
            AnswerButton(
                label        = "Verdadero",
                isAffirmative = true,
                enabled      = answering,
                onClick      = { quizVM.answerQuestion(true) }
            )
            AnswerButton(
                label        = "Falso",
                isAffirmative = false,
                enabled      = answering,
                onClick      = { quizVM.answerQuestion(false) }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Mini score en vivo
            LiveScoreRow(
                correct   = quizVM.score,
                incorrect = quizVM.incorrectCount,
                remaining = quizVM.totalQuestions - quizVM.currentIndex
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ── Header azul ───────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizHeader(
    currentIndex: Int,
    total: Int,
    timerValue: Int,
    navController: NavController
) {
    val displayIndex = (currentIndex + 1).coerceAtMost(total)
    val progress     = displayIndex.toFloat() / total

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(QuizBlue)
    ) {
        TopAppBar(
            title = {
                Text(
                    text      = "Prueba de conocimientos",
                    color     = QuizWhite,
                    fontSize  = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector     = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint            = QuizWhite
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )

        Text(
            text     = "PREGUNTA $displayIndex DE $total",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color    = QuizWhite.copy(alpha = 0.55f),
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress     = { progress },
            modifier     = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(50)),
            color        = QuizWhite,
            trackColor   = QuizWhite.copy(alpha = 0.2f),
            strokeCap    = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// ── Tarjeta de pregunta con timer ─────────────────────────────────────────────
@Composable
fun QuestionCard(question: String, timerValue: Int, maxTimer: Int) {
    val timerProgress by animateFloatAsState(
        targetValue   = timerValue.toFloat() / maxTimer,
        animationSpec = tween(durationMillis = 300),
        label         = "timer"
    )
    val timerColor by animateColorAsState(
        targetValue   = when {
            timerValue > 6 -> QuizBlue
            timerValue > 3 -> Color(0xFFF59E0B)
            else           -> QuizRed
        },
        animationSpec = tween(durationMillis = 500),
        label         = "timerColor"
    )

    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(20.dp),
        colors    = CardDefaults.cardColors(containerColor = QuizWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Timer circular
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(72.dp)) {
                androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                    val stroke = 6.dp.toPx()
                    drawArc(
                        color      = QuizLightBlue,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter  = false,
                        style      = androidx.compose.ui.graphics.drawscope.Stroke(
                            width  = stroke,
                            cap    = StrokeCap.Round
                        )
                    )
                    drawArc(
                        color      = timerColor,
                        startAngle = -90f,
                        sweepAngle = 360f * timerProgress,
                        useCenter  = false,
                        style      = androidx.compose.ui.graphics.drawscope.Stroke(
                            width  = stroke,
                            cap    = StrokeCap.Round
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text       = timerValue.toString(),
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color      = timerColor
                    )
                    Text(
                        text     = "seg",
                        fontSize = 9.sp,
                        color    = QuizTextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text       = question,
                fontSize   = 15.sp,
                fontWeight = FontWeight.Medium,
                color      = QuizTextPrimary,
                lineHeight = 23.sp,
                textAlign  = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text     = "¿Verdadero o falso?",
                fontSize = 11.sp,
                color    = QuizTextMuted
            )
        }
    }
}

// ── Botones de respuesta ──────────────────────────────────────────────────────
@Composable
fun AnswerButton(
    label: String,
    isAffirmative: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val iconBg  = if (isAffirmative) QuizLightBlue else QuizRedLight
    val iconTint = if (isAffirmative) QuizBlue else QuizRed
    val icon    = if (isAffirmative) Icons.Filled.Check else Icons.Filled.Close
    val hint    = if (isAffirmative) "El enunciado es correcto"
    else               "El enunciado es incorrecto"

    OutlinedButton(
        onClick  = onClick,
        enabled  = enabled,
        shape    = RoundedCornerShape(14.dp),
        colors   = ButtonDefaults.outlinedButtonColors(
            containerColor         = QuizWhite,
            contentColor           = QuizTextPrimary,
            disabledContainerColor = QuizWhite.copy(alpha = 0.5f),
            disabledContentColor   = QuizTextMuted
        ),
        border   = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder),
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Row(
            modifier            = Modifier.fillMaxWidth(),
            verticalAlignment   = Alignment.CenterVertically
        ) {
            Box(
                modifier        = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = icon,
                    contentDescription = null,
                    tint               = iconTint,
                    modifier           = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text       = label,
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = if (enabled) QuizTextPrimary else QuizTextMuted
                )
                Text(
                    text     = hint,
                    fontSize = 11.sp,
                    color    = QuizTextMuted
                )
            }
        }
    }
}

// ── Banner de feedback inmediato ──────────────────────────────────────────────
@Composable
fun FeedbackBanner(correct: Boolean, correctAnswer: Boolean) {
    val bg      = if (correct) QuizGreenLight else QuizRedLight
    val border  = if (correct) QuizGreen      else QuizRed
    val icon    = if (correct) Icons.Filled.Check else Icons.Filled.Close
    val tint    = if (correct) QuizGreen      else QuizRed
    val title   = if (correct) "¡Correcto!" else "Incorrecto"
    val subtitle = if (correct) "¡Sigue así!"
    else         "La respuesta era ${if (correctAnswer) "Verdadero" else "Falso"}."

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(14.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = icon,
            contentDescription = null,
            tint               = tint,
            modifier           = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text       = title,
                fontSize   = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color      = if (correct) QuizGreenDark else QuizRedDark
            )
            Text(
                text     = subtitle,
                fontSize = 11.sp,
                color    = if (correct) QuizGreen else QuizRed
            )
        }
    }
}

// ── Score en vivo ─────────────────────────────────────────────────────────────
@Composable
fun LiveScoreRow(correct: Int, incorrect: Int, remaining: Int) {
    Row(
        modifier            = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ScoreMiniCard(
            modifier = Modifier.weight(1f),
            label    = "CORRECTAS",
            value    = correct.toString(),
            color    = QuizGreen
        )
        ScoreMiniCard(
            modifier = Modifier.weight(1f),
            label    = "INCORRECTAS",
            value    = incorrect.toString(),
            color    = QuizRed
        )
        ScoreMiniCard(
            modifier = Modifier.weight(1f),
            label    = "RESTANTES",
            value    = remaining.toString(),
            color    = QuizBlue
        )
    }
}

@Composable
fun ScoreMiniCard(modifier: Modifier, label: String, value: String, color: Color) {
    Column(
        modifier            = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(QuizWhite)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text          = label,
            fontSize      = 9.sp,
            fontWeight    = FontWeight.SemiBold,
            color         = QuizTextMuted,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text       = value,
            fontSize   = 22.sp,
            fontWeight = FontWeight.Bold,
            color      = color
        )
    }
}

// ── Pantalla de fin de quiz ───────────────────────────────────────────────────
@Composable
fun QuizFinishedScreen(
    score: Int,
    total: Int,
    onResults: () -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier            = Modifier
            .fillMaxSize()
            .background(QuizBg)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier        = Modifier
                .size(132.dp)
                .clip(CircleShape)
                .background(QuizLightBlue),
            contentAlignment = Alignment.Center
        ) {
//            Text(text = "🦷", fontSize = 36.sp)
            Image(painter = painterResource(id = com.example.myapplication.R.drawable.molly1), contentDescription = "Molly dentista")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text       = "Examen completado",
            fontSize   = 22.sp,
            fontWeight = FontWeight.Bold,
            color      = QuizTextPrimary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text      = "Obtuviste $score de $total respuestas correctas",
            fontSize  = 14.sp,
            color     = QuizTextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            onClick  = onResults,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape    = RoundedCornerShape(14.dp),
            colors   = ButtonDefaults.buttonColors(containerColor = QuizBlue)
        ) {
            Text(
                text       = "Ver mis resultados",
                fontWeight = FontWeight.SemiBold,
                fontSize   = 15.sp,
                color = QuizWhite
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick  = onRetry,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape    = RoundedCornerShape(14.dp),
            colors   = ButtonDefaults.outlinedButtonColors(contentColor = QuizTextPrimary),
            border   = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
        ) {
            Text(
                text     = "Intentar de nuevo",
                fontSize = 15.sp
            )
        }
    }
}