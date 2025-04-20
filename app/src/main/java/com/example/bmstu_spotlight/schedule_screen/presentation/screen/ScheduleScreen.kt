package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.schedule_screen.presentation.view_model.ScheduleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = koinViewModel()) {
    val schedule by viewModel.schedule
    ScheduleView(state = schedule)
}