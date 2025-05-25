package com.example.bmstu_spotlight.data.repository

import android.content.Context
import com.example.bmstu_spotlight.data.datasource.local.dao.BuildingDao
import com.example.bmstu_spotlight.data.datasource.local.dao.EdgeDao
import com.example.bmstu_spotlight.data.datasource.local.dao.FloorDao
import com.example.bmstu_spotlight.data.datasource.local.dao.NodeDao
import com.example.bmstu_spotlight.data.datasource.local.entities.BuildingEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.EdgeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.FloorEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.data.utils.getNodeTypeByReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.util.UUID

class EdgeJsonImporter(
    private val context: Context,
    private val nodeDao: NodeDao,
    private val edgeDao: EdgeDao,
    private val floorDao: FloorDao,
    private val buildingDao: BuildingDao
) {
    suspend fun importAllFromJson() = withContext(Dispatchers.IO) {
        val jsonString = context.assets.open("graph_edges.json")
            .bufferedReader().use { it.readText() }

        val rawMap: Map<String, Map<String, Double>> = Json.decodeFromString(jsonString)

        val allReferences = (rawMap.keys + rawMap.values.flatMap { it.keys }).toSet()

        val defaultFloorId = ensureDefaultFloor()

        val nodeEntities = allReferences.map { reference ->
            val type = getNodeTypeByReference(reference)

            NodeEntity(
                nodeId = UUID.randomUUID(),
                nodeName = reference,
                nodeDescription = "",
                xCoordinate = 0f,
                yCoordinate = 0f,
                nodeType = type,
                roomNumber = if (type == NodeType.CLASSROOM) reference else null,
                isAvailable = true,
                foreignFloorId = defaultFloorId,
                reference = reference,
                floor = 6
            )
        }

        nodeDao.insertAll(nodeEntities)

        val allNodes = nodeDao.readAll().first()
        val referenceMap = allNodes.associate { it.reference to it.nodeId }

        val edgeEntities = rawMap.flatMap { (fromRef, neighbors) ->
            neighbors.mapNotNull { (toRef, weight) ->
                val fromId = referenceMap[fromRef]
                val toId = referenceMap[toRef]
                if (fromId != null && toId != null) {
                    EdgeEntity(fromNodeId = fromId, toNodeId = toId, weight = weight)
                } else null
            }
        }

        edgeDao.insertAll(edgeEntities)
    }

    private suspend fun ensureDefaultFloor(): UUID = withContext(Dispatchers.IO) {
        val existingFloors = floorDao.readAll().firstOrNull().orEmpty()
        if (existingFloors.isNotEmpty()) return@withContext existingFloors.first().floorId

        val newFloorId = UUID.randomUUID()
        val buildingId = ensureDefaultBuilding()

        val floor = FloorEntity(
            floorId = newFloorId,
            floorNumber = 1,
            mapRef = "default_map",
            scale = 1f,
            foreignBuildingId = buildingId
        )
        floorDao.insert(floor)
        return@withContext newFloorId
    }

    private suspend fun ensureDefaultBuilding(): UUID = withContext(Dispatchers.IO) {
        val existingBuildings = buildingDao.readAll().firstOrNull().orEmpty()
        if (existingBuildings.isNotEmpty()) return@withContext existingBuildings.first().buildingId

        val newBuildingId = UUID.randomUUID()

        val building = BuildingEntity(
            buildingId = newBuildingId,
            buildingName = "Главный корпус",
            buildingDescription = "Автоматически добавлен",
            buildingAddress = "Москва, ул. 2-я Бауманская, д. 5"
        )

        buildingDao.insert(building)

        return@withContext newBuildingId
    }
}
