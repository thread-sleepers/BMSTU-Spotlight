package com.example.bmstu_spotlight.schedule_screen.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.schedule_screen.data.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduleCase: GetScheduleCase
) : ViewModel() {
    private val _schedule = mutableStateOf<List<Lesson>>(emptyList())
    val schedule: State<List<Lesson>> = _schedule

    init {
        loadSchedule()
    }

    private fun loadSchedule() {
        viewModelScope.launch {
            val scheduleData = getScheduleCase()
            _schedule.value = scheduleData
        }
    }
}

