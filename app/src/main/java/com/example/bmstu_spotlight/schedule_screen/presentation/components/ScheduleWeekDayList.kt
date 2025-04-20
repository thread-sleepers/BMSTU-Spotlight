package com.example.bmstu_spotlight.schedule_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleWeekDayList(
    items: List<String>,
    lessons: Map<String, String>,
    modifier: Modifier
) {
    LazyColumn(
    ) {
        items(items) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.height(420.dp)
            ) {
                Text(text="$item")
                ScheduleList(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 32.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                    items = lessons
                )
            }
        }
    }
}