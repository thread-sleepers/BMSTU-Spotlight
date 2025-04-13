package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun SavedLocationsScreen() {
    val recent = mapOf(
        "Bauman Racing Team" to "5 мин",
        "Аудитория 395" to "22 мин",
        "Кафе 'Чайная пара'" to "8 мин"
    )

    val favourites = mapOf(
        "Koнгресс-Холл" to "25 мин",
        "Читальный зал старших курсов" to "18 мин"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Title Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Destinations",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
            )
        }

        // Recent Section
        SectionHeader(title = "Recent", paddingStart = 32.dp)
        LocationList(
            items = recent,
            icon = Icons.Default.FavoriteBorder,
            iconContentDescription = "Recent Location",
            itemPaddingStart = 32.dp
        )

        // Saved Section
        SectionHeader(title = "Saved", paddingStart = 32.dp)
        LocationList(
            items = favourites,
            icon = Icons.Default.Favorite,
            iconContentDescription = "Saved Location",
            itemPaddingStart = 32.dp
        )
    }
}

@Composable
private fun SectionHeader(title: String, paddingStart: Dp) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = paddingStart,
                top = 24.dp,
                bottom = 8.dp
            )
        )
    }
}

@Composable
private fun LocationList(
    items: Map<String, String>,
    icon: ImageVector,
    iconContentDescription: String,
    itemPaddingStart: Dp
) {
    LazyColumn {
        items(items.entries.toList()) { (location, time) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(
                    start = itemPaddingStart
                )
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconContentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = location,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(32.dp)
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = iconContentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = time,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
    }
}



