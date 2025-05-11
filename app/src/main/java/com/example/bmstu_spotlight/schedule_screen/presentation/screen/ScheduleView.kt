package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.presentation.components.ScheduleList
import com.example.bmstu_spotlight.schedule_screen.presentation.components.ScheduleWeekDayList
//import com.example.bmstu_spotlight.ui.theme.ColorBack3

@Composable
fun ScheduleView(state: List<Lesson>) {
    Column(modifier = Modifier
        //.background(Color.White)
        .fillMaxSize()
        .padding(8.dp)) {
        CustomTopBar(stringResource(R.string.schedule))
        val weekDays = remember { listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота") }
        ScheduleWeekDayList(weekDays, state, modifier = Modifier)
    }
}