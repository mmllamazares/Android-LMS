package com.example.myapplication.mainView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.navigation.AppScreens
import com.example.myapplication.userName.UserNameViewModel

// colores
private val PrimaryBlue = Color(0xFF1A3ADB)
private val LightBlue = Color(0xFFEEF1FF)
private val AccentBlue = Color(0xFF4A6CF7)
private val TextPrimary = Color(0xFF0D0D2B)
private val TextSecondary = Color(0xFF6B7280)
val BackgroundGray = Color(0xFFF5F6FA)
private val CardWhite = Color(0xFFFFFFFF)
private val GreenProgress = Color(0xFF10B981)
private val TagBackground = Color(0xFFF0F0F5)




@Composable
fun MainView(navController: NavController) {
    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val courses by viewModel.courses.collectAsState()

    Scaffold(
        topBar = { TopBarHeaderMix(searchText,viewModel) },
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
            items(courses.size) { index ->
                CourseCard(course = courses[index], navController)
//                CourseCard(course = Courses.dummyCourses.filter { it.title.contain("") }[index])
            }
//            items(Courses.dummyCourses.size) { index ->
//                CourseCard(course = Courses.dummyCourses[index])
////                CourseCard(course = Courses.dummyCourses.filter { it.title.contain("") }[index])
//            }
//            items(1) {
//                Text(text = "Hola mundo")
//            }
        }
    }
}

@Composable
fun TopBarHeaderMix(searchText: String, viewModel: MainViewModel) {
    Column() {
        Header()
        Spacer(modifier = Modifier.padding(5.dp))
//            SearchTextField(value = textFieldState, onValueChange = { textFieldState = it })
        SearchBar(searchText,viewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        TitleSection()
    }
}

@Composable
fun Header(headerText: String = "EstomatoLearn") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
//            .padding(top=30.dp)
            .background(Color.White)
    ) {
        Text(
            text = "$headerText",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp, start = 16.dp)
        )
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        TextField(
            value = value, onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Buscar") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
        )
    }
}

@Composable
fun SearchBar(searchText: String, viewModel: MainViewModel) {
//    var query by remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        placeholder = {
            Text(
                "Buscar temas",
                color = TextSecondary,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(Icons.Outlined.Search, contentDescription = null, tint = TextSecondary)
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = CardWhite,
            focusedContainerColor = CardWhite,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = AccentBlue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
    )
}

@Composable
fun TitleSection(
    titleText: String = "Operatoria Técnica",
    titleDesc: String = "Especialidad de odontología restauradora"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column() {
            Text(text = titleText, style = MaterialTheme.typography.headlineMedium)
            Text(text = titleDesc, style = MaterialTheme.typography.titleMedium)

        }
    }
}

@Composable
fun CourseCard(course: CourseItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Imagen + info
            Row(verticalAlignment = Alignment.Top) {
                // Imagen placeholder
                Box(
                    modifier = Modifier
                        .size(width = 90.dp, height = 70.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(course.imageColor, course.imageColor.copy(alpha = 0.7f))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        //medical services icon
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = TextPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = course.description,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progreso
            if (course.progress != null) {
                ProgressSection(course)
            }

            // Tags (duración / módulos)
            if (course.duration != null || course.modules != null) {
                TagsSection(course)
            }

            // Avatares estudiantes
            if (course.students != null && course.avatarColors != null) {
                StudentsSection(course)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón
            CourseButton(
                label = course.buttonLabel,
                isPrimary = course.progress != null, navController
            )
        }
    }
}

@Composable
fun ProgressSection(course: CourseItem) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${((course.progress ?: 0f) * 100).toInt()}% Completado",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = GreenProgress
            )
            Text(
                text = "${course.completedLessons}/${course.totalLessons} Lecciones",
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = { course.progress ?: 0f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50)),
            color = PrimaryBlue,
            trackColor = LightBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun TagsSection(course: CourseItem) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        course.duration?.let {
            TagChip(icon = Icons.Outlined.DateRange, label = it)
        }
        course.modules?.let {
            TagChip(icon = Icons.Outlined.MailOutline, label = "$it módulos")
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun TagChip(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TagBackground)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(13.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}

@Composable
fun StudentsSection(course: CourseItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-8).dp)
    ) {
        course.avatarColors?.forEach { color ->
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(1.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "+${course.students} estudiantes",
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CourseButton(label: String, isPrimary: Boolean,navController: NavController) {
    if (isPrimary) {
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
    } else {
        OutlinedButton(
            onClick = {navController.navigate("course_detail_view")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary)
        ) {
            val icon = if (label.contains("Empezar")) Icons.AutoMirrored.Filled.ArrowForward
            else Icons.Outlined.Info
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(label, fontWeight = FontWeight.Medium, fontSize = 14.sp)
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController
) {
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
            onClick = {navController.navigate(route= AppScreens.ProgressView.route)},
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


