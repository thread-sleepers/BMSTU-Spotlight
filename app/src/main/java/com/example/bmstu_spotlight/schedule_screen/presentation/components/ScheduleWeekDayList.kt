package com.example.bmstu_spotlight.schedule_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.ui.theme.Purple40
import java.time.format.TextStyle

@Composable
fun ScheduleWeekDayList(
    items: List<String>,
    lessons: List<Lesson>,
    modifier: Modifier
) {
    LazyColumn(
    ) {
        items(items) { item ->
            Column(
                modifier = modifier.height(600.dp).fillParentMaxWidth()
            ) {
                Text(text="${item}",
                    modifier = modifier.fillMaxWidth(),
                    color = Purple40,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center)
                ScheduleList(
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