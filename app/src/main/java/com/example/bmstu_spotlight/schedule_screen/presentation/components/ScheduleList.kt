package com.example.bmstu_spotlight.schedule_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.schedule_screen.presentation.model.LessonUi
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.Purple40
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson


@Composable
fun ScheduleList(
    items: List<LessonUi>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(25.dp)).background(MaterialTheme.colorScheme.outlineVariant)
    ) {
        if (items.isNotEmpty()) {
            items.forEach { lesson ->
                Row(
                    modifier = modifier
                        .height(100.dp)
                        .fillMaxWidth()
                ) {
                    val info = remember(lesson) { lesson.classInfo }
                    Text(
                        text = info,
                        modifier = modifier.weight(2.0f)
                    )
                    Button(
                        onClick = {},
                        modifier = modifier.weight(1.0f),
                        colors = ButtonColors(MaterialTheme.colorScheme.tertiary, Color.White, MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.tertiary)
                    ) {
                        val scheduleClassroom = remember(lesson) { lesson.location }
                        Text(text = scheduleClassroom)
                    }
                }
            }
        }
    }
}
