package com.example.bmstu_spotlight.schedule_screen.domain.model

data class Lesson(
    val startTime: String,
    val endTime: String,
    val name: String?,
    val typeOfLesson: String?,
    val classroom: String?
)