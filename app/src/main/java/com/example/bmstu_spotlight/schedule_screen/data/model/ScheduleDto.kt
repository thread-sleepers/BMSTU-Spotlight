package com.example.bmstu_spotlight.schedule_screen.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonDto(
    @SerialName("start_time")
    val startTime: String? = null,
    @SerialName("end_time")
    val endTime: String? = null,
    @SerialName("title")
    val name: String? = null,
    @SerialName("type")
    val typeOfLesson: String? = null,
    @SerialName("place")
    val classroom: String? = null
)

@Serializable
data class ScheduleDto(
    val items: List<LessonDto>
)