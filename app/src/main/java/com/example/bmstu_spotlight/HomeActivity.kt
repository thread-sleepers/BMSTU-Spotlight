package com.example.bmstu_spotlight

import android.annotation.SuppressLint


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.background
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import com.example.bmstu_spotlight.ui.theme.*
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightTheme
import kotlin.random.Random
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.clickable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightTheme
import androidx.compose.ui.platform.LocalContext


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMSTUSpotlightTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen( ) { // Экран меню
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Menu Screen", fontSize = 28.sp)
        MenuSection()
        BottomSection(context = context, currentRoute = Route.Home)
    }
}

@Composable
fun MenuSection() { // Отображение кнопок из меню

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight(0.9f),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("It's all in the Spotlight",
                    color = ColorText1,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Workshops",
                    color = ColorText2,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Creative Area",
                    color = ColorText2,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Classrooms",
                    color = ColorText1,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Coferences",
                    color = ColorText1,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("VK Education",
                    color = ColorText2,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }

        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Cafeterias and canteens",
                    color = ColorText2,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text("Shops",
                    color = ColorText1,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}
