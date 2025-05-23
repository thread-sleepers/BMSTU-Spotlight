package com.example.bmstu_spotlight.saved_locations_screen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.LocationList
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.SectionHeader

@Composable
fun SavedLocationsView(state1: Map<String, String>, state2: Map<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Локации",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
            )
        }

        SectionHeader(
            modifier = Modifier.fillMaxWidth(),
            title = "Недавние"
        )
        LocationList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 32.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            items = state1,
            icon = Icons.Default.FavoriteBorder,
            iconContentDescription = "Recent Location"
        )

        SectionHeader(
            modifier = Modifier.fillMaxWidth(),
            title = "Сохранённые"
        )
        LocationList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 32.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            items = state2,
            icon = Icons.Default.Favorite,
            iconContentDescription = "Saved Location"
        )
    }
}

