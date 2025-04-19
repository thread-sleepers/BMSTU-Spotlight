package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.schedule_screen.presentation.components.ScheduleList
import com.example.bmstu_spotlight.schedule_screen.presentation.components.ScheduleWeekDayList

@Composable
fun ScheduleView(state: Map<String, String>) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text(
            text = "Расписание",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
        )
        val weekDays = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")
        ScheduleWeekDayList(weekDays, state, modifier = Modifier)
    }
}