package com.example.bmstu_spotlight.schedule_screen.domain.repository

import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.model.toDomain
import org.koin.java.KoinJavaComponent.inject

class ScheduleRepository(
    private val networkService: NetworkService
) {
    val defaultList = listOf(
        Lesson(
            startTime = "aboba",
            endTime = "aboba",
            name = "aboba",
            typeOfLesson = "aboba",
            classroom = "aboba"
        )
    )
    suspend fun getSchedule(): List<Lesson> {
        val response = networkService.getSchedule()
        return response.body()?.items?.map {
            it.toDomain()
        } ?: defaultList
    }
}