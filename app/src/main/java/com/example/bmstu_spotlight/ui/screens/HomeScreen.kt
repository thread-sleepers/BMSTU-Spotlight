package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.Route
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorBack3
import com.example.bmstu_spotlight.ui.theme.ColorText1
import com.example.bmstu_spotlight.ui.theme.ColorText2

@Composable
fun HomeScreen(onHomeClicked: () -> Unit,
               onLocationClicked: () -> Unit,
               onFavoritesClicked : () -> Unit,
               onNotificationClicked : () -> Unit,
               onAccountClicked: () -> Unit) { // Экран меню
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Menu Screen", fontSize = 28.sp)
        MenuSection()
        BottomSection(currentRoute = Route.Home, onHomeClicked = onHomeClicked,
            onLocationClicked = onLocationClicked, onFavoritesClicked = onFavoritesClicked,
            onNotificationClicked = onNotificationClicked, onAccountClicked = onAccountClicked)
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
                Text("Conferences",
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
