package com.example.bmstu_spotlight.schedule_screen.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.schedule_screen.presentation.components.ScheduleWeekDayList
import com.example.bmstu_spotlight.schedule_screen.presentation.model.LessonUi

@Composable
fun ScheduleView(
    schedule: List<LessonUi>,
    isLoading: Boolean = false,
    isSuccess: Boolean = false,
    isError: Boolean = false,
    onRetry: () -> Unit,
    onNameClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CustomTopBar(stringResource(R.string.schedule))
        if (isSuccess) {
            val weekDays =
                remember {
                    listOf(
                        "Понедельник",
                        "Вторник",
                        "Среда",
                        "Четверг",
                        "Пятница",
                        "Суббота"
                    )
                }
            ScheduleWeekDayList(weekDays, schedule, onNameClick = {clickedName -> onNameClick(clickedName)}, modifier = Modifier)
        }
        if (isLoading) {
            LoadingView()
        }
        if (isError) {
            ErrorView { onRetry() }
        }
    }
}