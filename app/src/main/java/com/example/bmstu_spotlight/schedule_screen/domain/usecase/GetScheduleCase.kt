package com.example.bmstu_spotlight.schedule_screen.domain.usecase

import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class GetScheduleCase (
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(): Map<String, String> {
        return repository.getSchedule()
    }
}