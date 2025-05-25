package com.example.bmstu_spotlight.route.domain.usecases

import com.example.bmstu_spotlight.route.domain.repository.GraphRepository
import com.example.bmstu_spotlight.route.utils.mapToRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.roundToInt

class FindShortestPathUseCaseImpl(
    private val repository: GraphRepository
): FindShortestPathUseCase {
    data class Node(val name: String, val cost: Double) : Comparable<Node> {
        override fun compareTo(other: Node): Int = cost.compareTo(other.cost)
    }

    override suspend fun execute(start: String, end: String): FindShortestPathUseCase.Result {
        val graph = repository.getGraph()

        return withContext(Dispatchers.Default) {
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
                    val target = edge.to
                    if (newDist < distances.getValue(target)) {
                        distances[target] = newDist
                        previous[target] = current.name
                        queue.add(Node(target, newDist))
                    }
                }
            }

            if (!previous.containsKey(end)) return@withContext FindShortestPathUseCase.Result(
                -1.0,
                emptyList()
            )

            val path = mutableListOf<String>()
            var at: String? = end
            while (at != null) {
                path.add(at)
                at = previous[at]
            }
            path.reverse()

            val rawTime = distances[end] ?: -1.0

            // Преобразуем расстояние в минуты: нормализация в диапазон 1..10
            val normalizedTime = rawTime.mapToRange(
                oldMin = 0.0,
                oldMax = 230.0, // верхняя граница графа
                newMin = 1.0,
                newMax = 10.0
            ).roundToInt().toDouble()

            return@withContext FindShortestPathUseCase.Result(normalizedTime, path)
        }
    }
}