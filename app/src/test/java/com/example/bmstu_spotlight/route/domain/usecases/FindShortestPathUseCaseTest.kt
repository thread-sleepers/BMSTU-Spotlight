package com.example.bmstu_spotlight.route.domain.usecases

import com.example.bmstu_spotlight.route.domain.models.Graph
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FindShortestPathUseCaseTest {
    private lateinit var findShortestPathUseCase: FindShortestPathUseCase
    private lateinit var graph: Graph

    @Before
    fun setUp() {
        findShortestPathUseCase = FindShortestPathUseCaseImpl()
        graph = Graph()

        // Создаём тестовый граф
        graph.addEdge("A", "B", 5.0)
        graph.addEdge("A", "C", 10.0)
        graph.addEdge("B", "D", 2.0)
        graph.addEdge("C", "D", 1.0)
        graph.addEdge("B", "E", 3.0)
        graph.addEdge("D", "E", 8.0)
    }

    @Test
    fun `find shortest path A to E`() {
        val result = findShortestPathUseCase.execute(graph, "A", "E")

        assertEquals(8.0, result.time, 0.001) // Проверяем, что минимальное расстояние равно 8.0
        assertEquals(listOf("A", "B", "E"), result.path) // Проверяем, что путь корректный
    }

    @Test
    fun `find shortest path A to D`() {
        val result = findShortestPathUseCase.execute(graph, "A", "D")

        assertEquals(7.0, result.time, 0.001) // Кратчайший путь A → B → D (5 + 2)
        assertEquals(listOf("A", "B", "D"), result.path)
    }

    @Test
    fun `path does not exist`() {
        val result = findShortestPathUseCase.execute(graph, "A", "Z")

        assertEquals(-1.0, result.time, 0.001) // Если пути нет, то расстояние -1.0
        assertEquals(emptyList<String>(), result.path) // Пустой список пути
    }
}