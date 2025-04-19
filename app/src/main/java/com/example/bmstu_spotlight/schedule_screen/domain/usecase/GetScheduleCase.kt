package com.example.bmstu_spotlight.schedule_screen.domain.usecase

import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class GetScheduleCase (
    private val repository: ScheduleRepository
) {
    operator fun invoke(): Flow<Map<String, String>> {
        return repository.getSchedule()
    }
}