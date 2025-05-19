package com.example.bmstu_spotlight.schedule_screen.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import com.example.bmstu_spotlight.schedule_screen.presentation.model.LessonUi
import com.example.bmstu_spotlight.schedule_screen.presentation.model.toPresentation
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduleCase: GetScheduleCase
) : ViewModel() {
    private val _state = mutableStateOf<ResponseState.DataState>(ResponseState.DataState.LOADING)
    val state: State<ResponseState.DataState> = _state
    private val _schedule = mutableStateOf<List<LessonUi>>(emptyList())
    val schedule: State<List<LessonUi>> = _schedule

    init {
        loadSchedule()
    }

    //sth is wrong here
    internal fun loadSchedule() {
        viewModelScope.launch {
            if (getScheduleCase().data.isNullOrEmpty()) {               // if currentState is not Success
                _state.value = getScheduleCase.getState()
            } else {
                _schedule.value = getScheduleCase().data!!.map {
                    it.toPresentation()
                }
                _state.value = getScheduleCase.getState()
            }
        }
    }

}

