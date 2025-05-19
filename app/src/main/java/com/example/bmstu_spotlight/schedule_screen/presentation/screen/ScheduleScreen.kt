package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState
import com.example.bmstu_spotlight.schedule_screen.presentation.view_model.ScheduleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = koinViewModel()) {
    val schedule by viewModel.schedule.collectAsState()
    val dataState by viewModel.state.collectAsState()
    val onRetry = {
        viewModel.loadSchedule()
    }

    when (dataState) {
        ResponseState.DataState.LOADING -> ScheduleView(
            schedule = schedule,
            isLoading = true,
            onRetry = onRetry)

        ResponseState.DataState.SUCCESS -> ScheduleView(
            schedule = schedule,
            isSuccess = true,
            onRetry = onRetry)

        ResponseState.DataState.ERROR -> ScheduleView(
            schedule = schedule,
            isError = true,
            onRetry = onRetry)
    }
}