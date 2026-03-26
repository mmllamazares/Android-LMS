package com.example.myapplication.progressView

import android.content.Context
import android.content.Intent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import kotlin.compareTo
import kotlin.div
import kotlin.text.toFloat
import kotlin.toString

// ── Colores ───────────────────────────────────────────────────────────────────
private val PrimaryBlue = Color(0xFF1A3ADB)
private val LightBlue = Color(0xFFEEF1FF)
private val BackgroundStart = Color(0xFFF0F2FF)
private val BackgroundEnd = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF0D0D2B)
private val TextSecondary = Color(0xFF6B7280)
private val GreenCorrect = Color(0xFF10B981)
private val RedIncorrect = Color(0xFFEF4444)
private val WhatsAppGreen = Color(0xFF25D366)
private val CircleTrack = Color(0xFFE5E7EB)

// ── Vista principal ───────────────────────────────────────────────────────────
@Composable
fun ProgressView(
    userName: String = "Dr. Morales",
    moduleName: String = "Endodoncia Avanzada",
    score: Int = 90,
    maxScore: Int = 100,
    correct: Int = 18,
    incorrect: Int = 2,
    onBack: () -> Unit = {},
    onShare: () -> Unit = {},
    onReview: () -> Unit = {},
    navController: NavController,
    viewModel: ProgressViewModel
) {
    val context = LocalContext.current
    // Observar datos de Room
    val quizScore by viewModel.quizScore.observeAsState(0)
    val quizTotalQuestions by viewModel.quizTotalQuestions.observeAsState(0)
    val userName by viewModel.userName.observeAsState("Usuario")

    // Calcular respuestas correctas e incorrectas
    val correct = quizScore
    val incorrect = quizTotalQuestions - quizScore

//    val shareViaWhatsApp = {
//        shareResultsToWhatsApp(
//            context = context,
//            userName = userName,
//            moduleName = moduleName,
//            score = quizScore,
//            totalQuestions = quizTotalQuestions,
//            correctAnswers = correct
//        )
//    }

    val shareViaWhatsApp = {
        ShareCardGenerator.generateAndShare(
            context        = context,
            userName       = userName,
            moduleName     = moduleName,
            score          = quizScore,
            totalQuestions = quizTotalQuestions
        )
    }

// Animación del círculo de progreso al entrar
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(quizScore) {
        animatedProgress.animateTo(
            targetValue = if (quizTotalQuestions > 0)
                quizScore / quizTotalQuestions.toFloat()
            else 0f,
            animationSpec = tween(durationMillis = 1200, easing = EaseOutCubic)
        )
    }

    Scaffold(
        topBar = { ResultTopBar(onBack = {}, navController) },
        containerColor = BackgroundStart
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            BadgeIcon()
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡Buen trabajo, $userName!",  // ← Ahora usa el nombre de Room
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Has completado el examen.",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(36.dp))

            ScoreCircle(
                score = quizScore,  // ← Usa datos de Room
                maxScore = quizTotalQuestions,
                progress = animatedProgress.value
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.CheckCircle,
                    iconColor = GreenCorrect,
                    label = "CORRECTAS",
                    value = correct.toString()  // ← Ahora usa datos de Room
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Clear,
                    iconColor = RedIncorrect,
                    label = "INCORRECTAS",
                    value = incorrect.toString()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón compartir WhatsApp
            Button(
                onClick = shareViaWhatsApp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Compartir con mi profesor",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Revisar errores
//            TextButton(onClick = onReview) {
//                Icon(
//                    imageVector = Icons.Outlined.Refresh,
//                    contentDescription = null,
//                    tint = TextSecondary,
//                    modifier = Modifier.size(16.dp)
//                )
//                Spacer(modifier = Modifier.width(6.dp))
//                Text(
//                    text = "Revisar mis errores",
//                    color = TextSecondary,
//                    fontSize = 14.sp
//                )
//            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ── Función para compartir por WhatsApp ───────────────────────────────────────
private fun shareResultsToWhatsApp(
    context: Context,
    userName: String,
    moduleName: String,
    score: Int,
    totalQuestions: Int,
    correctAnswers: Int
) {
    val percentage = if (totalQuestions > 0) (score * 100) / totalQuestions else 0

    val message = """
        🦷 *Resultados EstomatoLearn*
        
        ¡Hola! Soy *$userName*.
        
        Acabo de completar el módulo: *$moduleName*
        
        📊 *Calificación:* $score/$totalQuestions ($percentage%)
        ✅ Correctas: $correctAnswers
        ❌ Incorrectas: ${totalQuestions - correctAnswers}
        
        Enviado desde la app EstomatoLearn 📱
    """.trimIndent()

    // Intent específico para WhatsApp
    val whatsappIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
        `package` = "com.whatsapp"
    }

    // Verificar si WhatsApp está instalado
    val packageManager = context.packageManager
    val resolveInfo = packageManager.queryIntentActivities(whatsappIntent, 0)

    if (resolveInfo.isNotEmpty()) {
        // WhatsApp está instalado, abrir directamente
        context.startActivity(whatsappIntent)
    } else {
        // WhatsApp no está instalado, usar chooser genérico
        val genericIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(
            Intent.createChooser(
                genericIntent,
                "Compartir resultados vía"
            )
        )
    }
}

// ── Top Bar ───────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultTopBar(onBack: () -> Unit, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Resultado del Curso",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = TextPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = TextPrimary,
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones", tint = TextPrimary)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

// ── Badge icono ───────────────────────────────────────────────────────────────
@Composable
fun BadgeIcon() {
    Box(
        modifier = Modifier
            .size(132.dp)
            .clip(CircleShape)
            .background(LightBlue),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.molly1), contentDescription = "Molly dentista")
//        Icon(
////            imageVector = Icons.Filled.MilitaryTech,
//            imageVector = Icons.Filled.AccountCircle,
//            contentDescription = null,
//            tint = PrimaryBlue,
//            modifier = Modifier.size(36.dp)
//        )
    }
}

// ── Círculo de puntuación ─────────────────────────────────────────────────────
@Composable
fun ScoreCircle(score: Int, maxScore: Int, progress: Float) {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 18.dp.toPx()
            val radius = (size.minDimension - strokeWidth) / 2
            val topLeft = Offset(
                x = (size.width - radius * 2) / 2,
                y = (size.height - radius * 2) / 2
            )
            val arcSize = Size(radius * 2, radius * 2)

            // Track (fondo gris)
            drawArc(
                color = CircleTrack,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progreso (azul animado)
            drawArc(
                color = PrimaryBlue,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        // Texto del score en el centro
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = score.toString(),
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 52.sp
            )
            Text(
                text = "/ $maxScore",
                fontSize = 16.sp,
                color = TextSecondary
            )
        }
    }
}

// ── Tarjeta de estadística ────────────────────────────────────────────────────
@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    label: String,
    value: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
    }
}