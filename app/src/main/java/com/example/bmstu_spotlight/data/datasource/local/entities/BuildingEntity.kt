package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "buildings")
data class BuildingEntity(
    @PrimaryKey
    @ColumnInfo(name = "building_id")
    val buildingId: UUID,
    @ColumnInfo(name = "name")
    val buildingName: String,
    @ColumnInfo(name = "description")
    val buildingDescription: String,
    @ColumnInfo(name = "address")
    val buildingAddress: String
)
