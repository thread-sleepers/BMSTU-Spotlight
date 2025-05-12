package com.example.bmstu_spotlight.schedule_screen.domain.repository
//
//import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
//import kotlinx.coroutines.flow.Flow
//
//interface ScheduleRepository {
//    suspend fun getSchedule(): List<Lesson>
//}
import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.model.toDomain
import org.koin.java.KoinJavaComponent.inject

class ScheduleRepository {
    suspend fun getSchedule(): List<Lesson> {
        val response = NetworkService.getSchedule()
        return response.body()?.items?.map {
            it.toDomain()
        } ?: listOf() //stub
    }
}