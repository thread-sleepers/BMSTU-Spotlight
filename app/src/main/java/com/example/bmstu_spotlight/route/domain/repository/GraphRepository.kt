package com.example.bmstu_spotlight.route.domain.repository

import com.example.bmstu_spotlight.route.domain.models.Graph

interface GraphRepository {
    suspend fun getGraph(): Graph
}