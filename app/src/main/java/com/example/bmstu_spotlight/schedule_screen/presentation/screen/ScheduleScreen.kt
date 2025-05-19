package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState
import com.example.bmstu_spotlight.schedule_screen.presentation.view_model.ScheduleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = koinViewModel()) {
    val schedule by viewModel.schedule
    val dataState by viewModel.state

    when (dataState) {
        ResponseState.DataState.LOADING -> ScheduleView(
            schedule = schedule,
            isLoading = true,
            onRetry = { viewModel.loadSchedule() })

        ResponseState.DataState.SUCCESS -> ScheduleView(
            schedule = schedule,
            isSuccess = true,
            onRetry = { viewModel.loadSchedule() })

        ResponseState.DataState.ERROR -> ScheduleView(
            schedule = schedule,
            isError = true,
            onRetry = { viewModel.loadSchedule() })
    }
}