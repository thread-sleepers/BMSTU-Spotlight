package com.example.bmstu_spotlight.route.domain.usecases

import com.example.bmstu_spotlight.route.domain.models.Graph
import java.util.*

class FindShortestPathUseCaseImpl: FindShortestPathUseCase {
    data class Node(val name: String, val cost: Double) : Comparable<Node> {
        override fun compareTo(other: Node): Int = cost.compareTo(other.cost)
    }

    override fun execute(graph: Graph, start: String, end: String): FindShortestPathUseCase.Result {
        val queue = PriorityQueue<Node>()
        val distances = mutableMapOf<String, Double>().withDefault { Double.MAX_VALUE }
        val previous = mutableMapOf<String, String?>()

        distances[start] = 0.0
        queue.add(Node(start, 0.0))

        while (queue.isNotEmpty()) {
            val current = queue.poll() ?: break

            if (current.name == end) break

            for (edge in graph.getEdges(current.name)) {
                val newDist = distances.getValue(current.name) + edge.weight
                if (newDist < distances.getValue(edge.target)) {
                    distances[edge.target] = newDist
                    previous[edge.target] = current.name
                    queue.add(Node(edge.target, newDist))
                }
            }
        }

        if (!previous.containsKey(end)) return FindShortestPathUseCase.Result(-1.0, emptyList())

        val path = mutableListOf<String>()
        var at: String? = end
        while (at != null) {
            path.add(at)
            at = previous[at]
        }
        path.reverse()

        return FindShortestPathUseCase.Result(distances[end] ?: -1.0, path)
    }
}