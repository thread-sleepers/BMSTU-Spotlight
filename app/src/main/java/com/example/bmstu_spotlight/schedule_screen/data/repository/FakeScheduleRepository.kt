package com.example.bmstu_spotlight.schedule_screen.data.repository

import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeScheduleRepository: ScheduleRepository {
    override fun getSchedule(): Flow<Map<String, String>> = flow {
        emit(mapOf(
            "Математичесиий анализ Лекция" to "8:30-10:00",
            "Математический анализ Семинар" to "10:10-11:40",
            "Обед" to "11:40-12:25",
            "История" to "12:25-13:55",
            "Физическая культура" to "14:05-15:35"
        ))
    }
}