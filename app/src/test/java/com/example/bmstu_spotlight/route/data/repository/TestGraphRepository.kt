package com.example.bmstu_spotlight.route.data.repository

import com.example.bmstu_spotlight.route.domain.models.Graph
import com.example.bmstu_spotlight.route.domain.repository.GraphRepository

class TestGraphRepository(private val testGraph: Graph) : GraphRepository {
    override suspend fun getGraph(): Graph = testGraph
}