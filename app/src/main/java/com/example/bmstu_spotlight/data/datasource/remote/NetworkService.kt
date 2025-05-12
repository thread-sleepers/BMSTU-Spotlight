package com.example.bmstu_spotlight.data.datasource.remote

import com.example.bmstu_spotlight.schedule_screen.data.model.ScheduleDto
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("/schedule")
    suspend fun getSchedule(): Response<ScheduleDto>
}