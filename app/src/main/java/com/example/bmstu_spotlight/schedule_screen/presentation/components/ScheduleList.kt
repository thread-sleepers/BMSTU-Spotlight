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



@Composable
fun ScheduleList(
    items: List<LessonUi>,
    onNameClick: (String) -> Unit,
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
                        onClick = {onNameClick(scheduleClassroom)},
                        modifier = modifier.weight(1.0f),
                        colors = ButtonColors(MaterialTheme.colorScheme.tertiary, Color.White, MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.tertiary)
                    ) {
                        val scheduleClassroom = remember(lesson) { lesson.location }
                        Text(text = scheduleClassroom)

 /*       items(items) { lesson  ->
            Row(
                modifier = modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                if (lesson.name == null) {
                    val scheduleRow = remember (lesson) { "${lesson.startTime}-${lesson.endTime} | " }
                    Text(text=scheduleRow,
                        modifier = modifier.weight(2.0f))
                }
                else {
                    if (lesson.typeOfLesson != null) {
                        val scheduleRow = remember (lesson) {"${lesson.startTime}-${lesson.endTime} | ${lesson.name}\n | ${lesson.typeOfLesson}"}
                        val scheduleClassroom = remember(lesson) {"${lesson.classroom}"}
                        Text(text=scheduleRow,
                            modifier = modifier.weight(2.0f))
                        Button(onClick = {onNameClick(scheduleClassroom)},
                            modifier = modifier.weight(1.0f),
                            colors = ButtonColors(MaterialTheme.colorScheme.tertiary, Color.White, MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.tertiary)
                        ) {
                            Text(text=scheduleClassroom)
                        }
                    } else {
                        val scheduleRow = remember(lesson) {"${lesson.startTime}-${lesson.endTime} | ${lesson.name}"}
                        Text(text=scheduleRow,
                            modifier = modifier.weight(2.0f))*/
                    }
                }
            }
        }
    }
}
