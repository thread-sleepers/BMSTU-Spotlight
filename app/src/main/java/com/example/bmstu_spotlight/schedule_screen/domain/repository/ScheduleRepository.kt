package com.example.bmstu_spotlight.schedule_screen.domain.repository

import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.model.toDomain

class ScheduleRepository(
    private val networkService: NetworkService
) {
    suspend fun getSchedule(): List<Lesson> {
        try {
            val response = networkService.getSchedule()
            if (!response.isSuccessful)
                throw Exception("HTTP Error: ${response.code()}")
            return response.body()?.items?.map{
                it.toDomain()
            } ?: emptyList()
        } catch(e: Exception) {
            print("Request threw an exception: $e")
            return emptyList()
        }
    }
}