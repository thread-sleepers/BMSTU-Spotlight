package com.example.bmstu_spotlight.route.utils

fun Double.mapToRange(
    oldMin: Double,
    oldMax: Double,
    newMin: Double,
    newMax: Double
) : Double {
    if (oldMax == oldMin) return newMin
    return ((this - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin
}