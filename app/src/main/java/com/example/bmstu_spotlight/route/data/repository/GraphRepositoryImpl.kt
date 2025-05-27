package com.example.bmstu_spotlight.route.data.repository

import com.example.bmstu_spotlight.data.datasource.local.dao.EdgeDao
import com.example.bmstu_spotlight.data.datasource.local.dao.NodeDao
import com.example.bmstu_spotlight.route.domain.models.Graph
import com.example.bmstu_spotlight.route.domain.repository.GraphRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class GraphRepositoryImpl(
    private val edgeDao: EdgeDao,
    private val nodeDao: NodeDao
): GraphRepository {
    override suspend fun getGraph(): Graph = withContext(Dispatchers.IO) {
        val edges = edgeDao.getAll().first()
        val nodes = nodeDao.readAll().first()

        val uuidToRef = nodes.associate { it.nodeId to it.reference }

        val graph = Graph()

        edges.forEach { edge ->
            val fromRef = uuidToRef[edge.fromNodeId]
            val toRef = uuidToRef[edge.toNodeId]

            if (fromRef != null && toRef != null) {
                graph.addEdge(fromRef, toRef, edge.weight)
            }
        }

        return@withContext graph
    }
}