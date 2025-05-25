package com.example.bmstu_spotlight.data.datasource.remote

import com.example.bmstu_spotlight.auth_screen.data.model.LoginRequest
import com.example.bmstu_spotlight.auth_screen.data.model.LoginResponse
import com.example.bmstu_spotlight.profile.data.model.UserProfileDto
import com.example.bmstu_spotlight.schedule_screen.data.model.ScheduleDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkService {
    @GET("/schedule")
    suspend fun getSchedule(): Response<ScheduleDto>
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("/profile")
    suspend fun getUserProfile(): Response<UserProfileDto>
}