package com.example.myapplication.mainView

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//import com.example.myapplication.progressView.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailView(courseItem: CourseItem, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = courseItem.title,
                        maxLines = 2,
                        color = Color.Black
//                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.Black
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )

//            HeaderDetail(headerText = courseItem.title, navController)
        },
        containerColor = BackgroundGray
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(all = 20.dp)
        ) {
            item { Text(text = courseItem.content) }
        }

    }
}


@Composable
fun HeaderDetail(headerText: String = "EstomatoLearn", navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()
//            .padding(top=30.dp)
            .background(Color.White)
    ) {
        Box(contentAlignment = Alignment.Center) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                //                tint = TextPrimary,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(top = 30.dp)
            )
        }

        Text(
            text = "$headerText",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp, start = 16.dp)
        )
    }
}