package com.example.bmstu_spotlight.schedule_screen.data.repository
//
//import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
//import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
//import com.example.bmstu_spotlight.schedule_screen.domain.model.toDomain
//
//class ScheduleRepository
//    @Inject
//    constructor(val scheduleService: NetworkService) {
//    suspend fun getSchedule(): List<Lesson> {
//        val response = scheduleService.getSchedule()
//        return response.body()?.items?.map {
//            it.toDomain()
//        } ?: listOf() //stub
//    }
//}
