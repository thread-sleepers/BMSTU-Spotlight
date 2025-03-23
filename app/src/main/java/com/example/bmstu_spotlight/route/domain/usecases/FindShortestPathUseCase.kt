package com.example.bmstu_spotlight.route.domain.usecases

import com.example.bmstu_spotlight.route.domain.models.Graph

interface FindShortestPathUseCase {
    data class Result(val time: Double, val path: List<String>)
    fun execute(graph: Graph, start: String, end: String): Result
}