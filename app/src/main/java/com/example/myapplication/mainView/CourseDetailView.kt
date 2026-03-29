package com.example.myapplication.mainView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ── Colores locales (los mismos de MainView) ──────────────────────────────────
private val DetailPrimaryBlue  = Color(0xFF1A3ADB)
private val DetailBlueDark     = Color(0xFF1530B8)
private val DetailLightBlue    = Color(0xFFEEF1FF)
private val DetailTextPrimary  = Color(0xFF0D0D2B)
private val DetailTextSecondary= Color(0xFF6B7280)
private val DetailTextBody     = Color(0xFF374151)
private val DetailDivider      = Color(0xFFF0F0F5)
private val DetailNoteBlue     = Color(0xFFEEF1FF)
private val DetailNoteText     = Color(0xFF1A3ADB)
private val DetailWhite        = Color(0xFFFFFFFF)

// ── Vista principal ───────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailView(courseItem: CourseItem, navController: NavController) {
    Scaffold(
        topBar = {
            CourseDetailHeader(courseItem = courseItem, navController = navController)
        },
        containerColor = DetailWhite
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = 24.dp, end = 24.dp,
                top = 28.dp,   bottom = 48.dp
            )
        ) {
            item {
                if (courseItem.imageRes != null) {
                    Image(
                        painter            = painterResource(id = courseItem.imageRes),
                        contentDescription = courseItem.title,
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier
                            .fillMaxWidth()
                            .height(220.dp).clip(RoundedCornerShape(12.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(courseItem.imageColor)
                    )
                }
            }

            // Chip de categoría
            item {
                Spacer(modifier = Modifier.height(24.dp))
                CategoryChip(label = courseItem.categoryLabel)
                Spacer(modifier = Modifier.height(16.dp))
            }
//            // Chip de categoría
//            item {
//                CategoryChip(label = courseItem.categoryLabel)
//                Spacer(modifier = Modifier.height(16.dp))
//            }

            // Título + descripción
            item {
                Text(
                    text = courseItem.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailTextPrimary,
                    lineHeight = 30.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = courseItem.description,
                    fontSize = 14.sp,
                    color = DetailTextSecondary,
                    lineHeight = 21.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                SectionDivider()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Contenido: secciones estructuradas o texto plano
            if (!courseItem.sections.isNullOrEmpty()) {
                items(courseItem.sections.size) { i ->
                    CourseContentSection(section = courseItem.sections[i])
                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                item {
                    Text(
                        text = courseItem.content,
                        fontSize = 15.sp,
                        color = DetailTextBody,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}

// ── Header azul con progreso ──────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailHeader(courseItem: CourseItem, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DetailPrimaryBlue)
    ) {
        // Barra de navegación transparente sobre el azul
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        // Contador de módulo
        if (courseItem.moduleIndex != null && courseItem.moduleTotal != null) {
            Text(
                text = "TEMA ${courseItem.moduleIndex} DE ${courseItem.moduleTotal}",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.55f),
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Título compacto en el header
        Text(
            text = courseItem.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = courseItem.categoryLabel,
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.65f),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Barra de progreso del módulo (opcional)
        if (courseItem.moduleIndex != null && courseItem.moduleTotal != null) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "Progreso del módulo",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.55f),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                LinearProgressIndicator(
                    progress = { courseItem.moduleIndex.toFloat() / courseItem.moduleTotal },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(50)),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.2f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ── Renderizado de secciones ──────────────────────────────────────────────────
@Composable
fun CourseContentSection(section: CourseSection) {
    when (section) {

        is CourseSection.PlainText -> {
            Text(
                text = section.body,
                fontSize = 15.sp,
                color = DetailTextBody,
                lineHeight = 24.sp
            )
        }

        is CourseSection.Heading -> {
            Text(
                text = section.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DetailTextPrimary,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = section.body,
                fontSize = 14.sp,
                color = DetailTextBody,
                lineHeight = 22.sp
            )
        }

        is CourseSection.AccentQuote -> {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(52.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(DetailPrimaryBlue.copy(alpha = 0.7f))
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = section.body,
                    fontSize = 14.sp,
                    color = DetailTextBody,
                    lineHeight = 22.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        is CourseSection.ClinicalNote -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(DetailNoteBlue)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(
                    text = section.body,
                    fontSize = 13.sp,
                    color = DetailNoteText,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

// ── Componentes menores ───────────────────────────────────────────────────────
@Composable
fun CategoryChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(DetailLightBlue)
            .padding(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = DetailPrimaryBlue
        )
    }
}

@Composable
fun SectionDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(DetailDivider)
    )
}