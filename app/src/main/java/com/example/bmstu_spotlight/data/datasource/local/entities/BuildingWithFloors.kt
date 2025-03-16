package com.example.bmstu_spotlight.data.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BuildingWithFloors(
    @Embedded
    val building: BuildingEntity,
    @Relation(
        parentColumn = "building_id",
        entityColumn = "foreign_building_id"
    )
    val floors: List<FloorEntity>
)