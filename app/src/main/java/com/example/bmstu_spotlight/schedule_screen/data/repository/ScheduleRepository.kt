package com.example.bmstu_spotlight.schedule_screen.data.repository

import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.model.toDomain
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState

class ScheduleRepository(
    private val networkService: NetworkService
) {
    suspend fun getSchedule(): ResponseState<List<Lesson>> {
        var currentState = ResponseState.loading<List<Lesson>>()
        try {
            val response = networkService.getSchedule()
            if (response.isSuccessful) {
                val data = response.body()?.items?.map {
                    it.toDomain()
                } ?: emptyList()
                currentState = ResponseState.success(data)
            } else {
                throw Exception("HTTP Error: ${response.code()}")
            }
        } catch(e: Exception) {
            print("Request threw an exception: $e")
            currentState = ResponseState.error()
        }
        return currentState
    }
}