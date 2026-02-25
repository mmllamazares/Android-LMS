package com.example.myapplication.mainView


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.navigation.AppScreens

private val PrimaryBlue = Color(0xFF1A3ADB)
private val LightBlue = Color(0xFFEEF1FF)
private val AccentBlue = Color(0xFF4A6CF7)
private val TextPrimary = Color(0xFF0D0D2B)
private val TextSecondary = Color(0xFF6B7280)
private val BackgroundGray = Color(0xFFF5F6FA)
private val CardWhite = Color(0xFFFFFFFF)
private val GreenProgress = Color(0xFF10B981)
private val TagBackground = Color(0xFFF0F0F5)

data class CourseItemTest(
    val title: String,
    val description: String,
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

private val dummyCourses = listOf(
    CourseItem(
        title = "Preparación de Cavidades",
        description = "Principios de Black y técnicas modernas de remoción de tejido.",
        progress = 0.65f,
        totalLessons = 18,
        completedLessons = 12,
        duration = null,
        modules = null,
        students = null,
        avatarColors = null,
        buttonLabel = "Continuar Aprendiendo",
        imageColor = Color(0xFF1E3A5F)
    ),
    CourseItem(
        title = "Materiales Dentales",
        description = "Resinas, ionómeros y adhesivos de última generación.",
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
    CourseItem(
        title = "Instrumental Quirúrgico",
        description = "Reconocimiento y uso de instrumental rotatorio y manual.",
        progress = null,
        totalLessons = null,
        completedLessons = null,
        duration = null,
        modules = null,
        students = 120,
        avatarColors = listOf(Color(0xFF6366F1), Color(0xFF10B981), Color(0xFFF59E0B)),
        buttonLabel = "Empezar Curso",
        imageColor = Color(0xFF1F4068)
    )
)

@Composable
fun MainViewTest(navController: NavController) {
    Scaffold(
        topBar = { TopBarHeaderMix() },
        bottomBar = {
            BottomNavBar(navController)
        }, containerColor = BackgroundGray
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(dummyCourses.size) { index ->
                CourseCard(course = dummyCourses[index])
            }
//            items(1) {
//                Text(text = "Hola mundo")
//            }
        }
    }
}


@Composable
fun BottomNavBar(
    navController: NavController
//    currentTab: CoursesTab,
//    onTabSelected: (CoursesTab) -> Unit
) {
//    val items = List(
//        Triple("Inicio",    Icons.Outlined.Home,        false),
//        Triple("Cursos",    Icons.Filled.MenuBook,      true),
//        Triple("Progreso",  Icons.Outlined.BarChart,    false),
//        Triple("Perfil",    Icons.Outlined.Person,      false),
//    )

    NavigationBar(
        containerColor = CardWhite,
        tonalElevation = 8.dp
    ) {

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Home, contentDescription = "Temas")
            },
            label = {
                Text("Temas", fontSize = 11.sp)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                indicatorColor = LightBlue,
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.CheckCircle, contentDescription = "Progreso")
            },
            label = {
                Text("Progreso", fontSize = 11.sp)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                indicatorColor = LightBlue,
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )
        NavigationBarItem(
            selected = false,
//            onClick = { onTabSelected(CoursesTab.Perfil) },
            onClick = { navController.navigate(route = AppScreens.UserView.route) },
            icon = {
                Icon(Icons.Outlined.Person, contentDescription = "Perfil")
            },
            label = {
                Text("Perfil", fontSize = 11.sp)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryBlue,
                selectedTextColor = PrimaryBlue,
                indicatorColor = LightBlue,
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )
    }
}