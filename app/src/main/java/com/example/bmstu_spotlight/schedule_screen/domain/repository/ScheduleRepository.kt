package com.example.bmstu_spotlight.schedule_screen.domain.repository

import com.example.bmstu_spotlight.schedule_screen.data.model.Lesson
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    suspend fun getSchedule(): List<Lesson>
}