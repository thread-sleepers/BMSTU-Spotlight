package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class FloorWithNodes(
    @Embedded
    val floor: FloorEntity,
    @Relation(
        parentColumn = "floor_id",
        entityColumn = "foreign_floor_id"
    )
    val nodes: List<NodeEntity>
)