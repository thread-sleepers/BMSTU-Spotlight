package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "nodes",
    indices = [Index("foreign_floor_id")],
    foreignKeys = [ForeignKey(
        entity = FloorEntity::class,
        parentColumns = ["floor_id"],
        childColumns = ["foreign_floor_id"],
        onUpdate = ForeignKey.CASCADE
    )]
)
data class NodeEntity(
    @PrimaryKey
    @ColumnInfo(name = "node_id")
    val nodeId: UUID,
    @ColumnInfo(name = "name")
    val nodeName: String,
    @ColumnInfo(name = "description")
    val nodeDescription: String,
    @ColumnInfo(name = "x_coordinate")
    val xCoordinate: Float,
    @ColumnInfo(name = "y_coordinate")
    val yCoordinate: Float,
    @ColumnInfo(name = "node_type")
    val nodeType: NodeType,
    @ColumnInfo(name = "room_number")
    val roomNumber: String?,
    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean,
    @ColumnInfo(name = "foreign_floor_id")
    val foreignFloorId: UUID,
    @ColumnInfo(name = "reference")
    val reference: String,
    @ColumnInfo(name = "floor")
    val floor: Int,
)

enum class NodeType {
    CLASSROOM,
    STAIRS,
    ELEVATOR,
    RESTROOM,
    LIBRARY,
    CANTEEN,
    HALL,
    LABORATORY
}