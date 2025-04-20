package com.example.bmstu_spotlight.schedule_screen.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduleCase: GetScheduleCase
) : ViewModel() {
    private val _schedule = mutableStateOf<Map<String, String>>(emptyMap())
    val schedule: State<Map<String, String>> = _schedule

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

