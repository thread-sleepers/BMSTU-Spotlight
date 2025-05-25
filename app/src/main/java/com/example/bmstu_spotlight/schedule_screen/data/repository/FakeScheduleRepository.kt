package com.example.bmstu_spotlight.schedule_screen.data.repository

import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeScheduleRepository: ScheduleRepository {
    override suspend fun getSchedule(): List<Lesson> =
        listOf(
            Lesson("8:30", "10:00", "Математический анализ", "Лекция","389"),
            Lesson("10:10", "11:40", "Математический анализ", "Семинар","384"),
            Lesson("11:40", "12:25","Обед",null,null),
            Lesson("12:25", "13:55", "История", "Лекция","Аудитория 430"),
            Lesson("14:05", "15:25", "Физическкая культура", null, null)
        )

}