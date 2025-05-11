package com.example.bmstu_spotlight.route.domain.usecases

interface FindShortestPathUseCase {
    data class Result(val time: Double, val path: List<String>)
    suspend fun execute(start: String, end: String): Result
}