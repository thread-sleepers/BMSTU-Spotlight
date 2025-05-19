package com.example.bmstu_spotlight.schedule_screen.presentation.model

import com.example.bmstu_spotlight.schedule_screen.domain.model.Lesson
import com.example.bmstu_spotlight.schedule_screen.domain.response_state.ResponseState

data class LessonUi(
    val classInfo: String,
    val location: String
)

fun Lesson.toPresentation(): LessonUi {
    var classInfo = "Нет информации о занятии"
    var itemName = ""

    val location =
        if (this.classroom.isNullOrEmpty()) {
            "Место проведения неизвестно"
        } else {
            this.classroom
        }
    val startTime =
        if (this.startTime.isNullOrEmpty()) {
            "Время начала неизвестно"
        } else {
            this.startTime
        }
    val endTime =
        if (this.endTime.isNullOrEmpty()) {
            "Время окончания неизвестно"
        } else {
            this.endTime
        }

    if (!this.name.isNullOrEmpty()) {
        itemName = this.name
    }
    if (!this.typeOfLesson.isNullOrEmpty() && itemName.isNotEmpty()) {
        classInfo = "$startTime-$endTime | $itemName\n | ${this.typeOfLesson}"
    } else {
        if (this.typeOfLesson.isNullOrEmpty() && itemName.isNotEmpty()) {
            classInfo = "$startTime-$endTime | $itemName\n"
        }
    }

    return LessonUi(
        classInfo = classInfo,
        location = location
    )
}