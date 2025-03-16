package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "floors",
    indices = [Index("foreign_building_id")],
    foreignKeys = [ForeignKey(
        entity = BuildingEntity::class,
        parentColumns = ["building_id"],
        childColumns = ["foreign_building_id"],
        onUpdate = ForeignKey.CASCADE
    )]
)
data class FloorEntity(
    @PrimaryKey
    @ColumnInfo(name = "floor_id")
    val floorId: UUID,
    @ColumnInfo(name = "floor_number")
    val floorNumber: Int,
    @ColumnInfo(name = "map_image_reference")
    val mapRef: String,
    @ColumnInfo(name = "scale")
    val scale: Float,
    @ColumnInfo(name = "foreign_building_id")
    val foreignBuildingId: UUID
)