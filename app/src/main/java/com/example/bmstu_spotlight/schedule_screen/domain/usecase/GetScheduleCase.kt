package com.example.bmstu_spotlight.schedule_screen.domain.usecase

import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.data.repository.ScheduleRepository
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState

class GetScheduleCase (
    private val repository: ScheduleRepository
) {
    fun getState(): ResponseState.DataState {
        return repository.currentState.state
    }
    suspend operator fun invoke(): ResponseState<List<Lesson>> {
       return repository.getSchedule()
    }
}