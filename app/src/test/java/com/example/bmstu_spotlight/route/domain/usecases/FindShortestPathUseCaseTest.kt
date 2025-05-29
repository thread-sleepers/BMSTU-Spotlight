package com.example.bmstu_spotlight.route.domain.usecases

import com.example.bmstu_spotlight.route.data.repository.TestGraphRepository
import com.example.bmstu_spotlight.route.domain.models.Graph
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FindShortestPathUseCaseTest {
    private lateinit var findShortestPathUseCase: FindShortestPathUseCase
    private lateinit var testGraph: Graph

    @Before
    fun setUp() {
        testGraph = Graph()

        testGraph.addEdge("350", "351", 10.0)
        testGraph.addEdge("351", "350", 10.0)

        testGraph.addEdge("350", "wc", 100.5)
        testGraph.addEdge("wc", "350", 100.5)

        testGraph.addEdge("350", "stairs_1", 101.98)
        testGraph.addEdge("stairs_1", "350", 101.98)

        testGraph.addEdge("351", "stairs_1", 92.2)
        testGraph.addEdge("stairs_1", "351", 92.2)

        testGraph.addEdge("350", "395", 158.11)
        testGraph.addEdge("395", "350", 158.11)

        testGraph.addEdge("384", "389", 5.90)

        testGraph.addEdge("404", "430", 47.18)

        val testRepository = TestGraphRepository(testGraph)
        findShortestPathUseCase = FindShortestPathUseCaseImpl(testRepository)
    }

    @Test
    fun `shortest time from 384 to 389`() = runBlocking {
        val result = findShortestPathUseCase.execute("384", "389")

        println("Computed path: ${result.path.joinToString(" → ")}")
        println("Computed time: ${result.time}")

        assertEquals(0.5, result.time, 0.001)
    }

    @Test
    fun `shortest time from 350 to stairs_1`() = runBlocking {
        val result = findShortestPathUseCase.execute("350", "stairs_1")

        println("Computed path: ${result.path.joinToString(" → ")}")
        println("Computed time: ${result.time}")

        assertEquals(4.5, result.time, 0.001)
    }

    @Test
    fun `shortest time from 350 to 395`() = runBlocking {
        val result = findShortestPathUseCase.execute("350", "395")

        println("Computed path: ${result.path.joinToString(" → ")}")
        println("Computed time: ${result.time}")

        assertEquals(7.0, result.time, 0.001)
    }

    @Test
    fun `direct path from 350 to stairs_1 is longer`() = runBlocking {
        val result = findShortestPathUseCase.execute("350", "stairs_1")

        println("Computed path: ${result.path.joinToString(" → ")}")
        println("Computed time: ${result.time}")

        assertEquals(4.5, result.time, 0.001)
    }

    @Test
    fun `path from 351 to wc`() = runBlocking {
        val result = findShortestPathUseCase.execute("351", "wc")

        assertEquals(5.0, result.time, 0.001)
    }

    @Test
    fun `shortest time from 404 to 430`() = runBlocking {
        val result = findShortestPathUseCase.execute("404", "430")

        println("Computed path: ${result.path.joinToString(" → ")}")
        println("Computed time: ${result.time}")

        assertEquals(2.25, result.time, 0.001)
    }

    @Test
    fun `path does not exist`() = runBlocking {
        val result = findShortestPathUseCase.execute("350", "non_existing_node")

        assertEquals(-1.0, result.time, 0.001)
    }
}