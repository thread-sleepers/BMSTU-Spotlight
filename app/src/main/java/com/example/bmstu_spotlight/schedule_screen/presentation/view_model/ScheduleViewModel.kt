package com.example.bmstu_spotlight.schedule_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduleCase: GetScheduleCase
) : ViewModel() {
    private val _schedule = MutableStateFlow<Map<String, String>>(emptyMap())
    val schedule: StateFlow<Map<String, String>> = _schedule

    init {
        loadSchedule()
    }

    private fun loadSchedule() {
        viewModelScope.launch {
            val schedule_get = getScheduleCase()
            schedule_get.collect{_schedule.value = it}
        }
    }
}

