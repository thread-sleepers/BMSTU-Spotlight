package com.example.bmstu_spotlight.schedule_screen.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.schedule_screen.presentation.model.LessonUi

@Composable
fun ScheduleWeekDayList(
    items: List<String>,
    lessons: List<LessonUi>,
    onNameClick: (String) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(items) { item ->
            Column(
                modifier = modifier
                    .height(600.dp)
                    .fillParentMaxWidth()
            ) {
                Text(
                    text = item,
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
                ScheduleList(
                    onNameClick = {clickedName -> onNameClick(clickedName)},
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                    items = lessons
                )
            }
        }
    }
}