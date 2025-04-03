package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.DataHolder

@Composable
fun FavoritesScreen() { // Экран любимых мест

    // Пример получения данных из датахолдера
    val location1 = DataHolder.location1
    val location2 = DataHolder.location2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Favorites Screen", fontSize = 28.sp)

        Text(text = "Location 1: $location1", fontSize = 18.sp)
        Text(text = "Location 2: $location2", fontSize = 18.sp)
    }

}