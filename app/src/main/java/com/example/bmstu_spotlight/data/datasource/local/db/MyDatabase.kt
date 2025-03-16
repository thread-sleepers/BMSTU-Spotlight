package com.example.bmstu_spotlight.data.datasource.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bmstu_spotlight.data.datasource.local.dao.BuildingDao
import com.example.bmstu_spotlight.data.datasource.local.dao.FloorDao
import com.example.bmstu_spotlight.data.datasource.local.dao.NodeDao
import com.example.bmstu_spotlight.data.datasource.local.entities.BuildingEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.FloorEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity

@Database(
    entities = [
        BuildingEntity::class,
        FloorEntity::class,
        NodeEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun buildingDao(): BuildingDao
    abstract fun floorDao(): FloorDao
    abstract fun nodeDao(): NodeDao
}

