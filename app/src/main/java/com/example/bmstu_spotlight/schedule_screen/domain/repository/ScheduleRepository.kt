package com.example.bmstu_spotlight.schedule_screen.domain.repository

import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(): Flow<Map<String, String>>
}