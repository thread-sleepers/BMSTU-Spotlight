package com.example.bmstu_spotlight.schedule_screen.domain.model

import com.example.bmstu_spotlight.schedule_screen.data.model.LessonDto

data class Lesson(
    val startTime: String,
    val endTime: String,
    val name: String?,
    val typeOfLesson: String?,
    val classroom: String?
)

fun LessonDto.toDomain() = Lesson(
    startTime = this.startTime,
    endTime = this.endTime,
    name = this.name,
    typeOfLesson = this.typeOfLesson,
    classroom = this.classroom
)