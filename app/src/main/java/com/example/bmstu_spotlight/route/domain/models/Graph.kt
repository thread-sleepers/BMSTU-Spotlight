package com.example.bmstu_spotlight.route.domain.models

class Graph {
    private val adjacencyList = mutableMapOf<String, MutableList<Edge>>()

    fun addEdge(from: String, to: String, weight: Double) {
        adjacencyList.computeIfAbsent(from) { mutableListOf() }.add(Edge(to, weight))
        adjacencyList.computeIfAbsent(to) { mutableListOf() }.add(Edge(from, weight))
    }

    fun getEdges(node: String): List<Edge> = adjacencyList[node] ?: emptyList()
    fun getNodes(): Set<String> = adjacencyList.keys
}