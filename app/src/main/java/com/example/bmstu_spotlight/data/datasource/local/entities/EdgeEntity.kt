package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.*
import java.util.UUID

@Entity(
    tableName = "edges",
    indices = [Index("from_node_id"), Index("to_node_id")],
    foreignKeys = [
        ForeignKey(
            entity = NodeEntity::class,
            parentColumns = ["node_id"],
            childColumns = ["from_node_id"],
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = NodeEntity::class,
            parentColumns = ["node_id"],
            childColumns = ["to_node_id"],
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EdgeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "from_node_id")
    val fromNodeId: UUID,
    @ColumnInfo(name = "to_node_id")
    val toNodeId: UUID,
    @ColumnInfo(name = "weight")
    val weight: Double
)