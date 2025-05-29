package com.example.bmstu_spotlight.location_screen.utils

import kotlin.math.roundToInt

fun formatTime(minutes: Double): String {
    val min = minutes.toInt()
    val secs = ((minutes - min) * 60).roundToInt()
    return when {
        min > 0 && secs > 0 -> "$min мин. $secs сек."
        min > 0 && secs == 0 -> "$min мин."
        min == 0 && secs > 0 -> "$secs сек."
        else -> "0 сек."
    }
}
