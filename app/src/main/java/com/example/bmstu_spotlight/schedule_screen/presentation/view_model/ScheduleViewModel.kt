package com.example.bmstu_spotlight.schedule_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import com.example.bmstu_spotlight.schedule_screen.presentation.model.LessonUi
import com.example.bmstu_spotlight.schedule_screen.presentation.model.toPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduleCase: GetScheduleCase
) : ViewModel() {
    private val _state = MutableStateFlow<ResponseState.DataState>(ResponseState.DataState.LOADING)
    val state: StateFlow<ResponseState.DataState> = _state.asStateFlow()
    private val _schedule = MutableStateFlow<List<LessonUi>>(emptyList())
    val schedule: StateFlow<List<LessonUi>> = _schedule.asStateFlow()

    init {
        loadSchedule()
    }

    internal fun loadSchedule() {
        _state.value = ResponseState.DataState.LOADING
        viewModelScope.launch {
            val response = getScheduleCase()
            if (response.data.isNullOrEmpty()) {
                _state.value = response.state
            } else {
                _schedule.value = response.data.map {
                    it.toPresentation()
                }
                _state.value = response.state
            }
        }
    }

}

