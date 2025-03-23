package com.example.bmstu_spotlight.route.domain.repository

import com.example.bmstu_spotlight.route.domain.models.Graph

interface GraphRepository {
    fun getGraph(): Graph
}