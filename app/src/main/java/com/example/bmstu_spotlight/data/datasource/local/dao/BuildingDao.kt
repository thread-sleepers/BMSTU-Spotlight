package com.example.bmstu_spotlight.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.bmstu_spotlight.data.datasource.local.entities.BuildingEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.BuildingWithFloors
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(building: BuildingEntity)

    @Delete(entity = BuildingEntity::class)
    suspend fun delete(building: BuildingEntity)

    @Query("SELECT * FROM buildings")
    fun readAll(): Flow<List<BuildingEntity>>

    @Transaction
    @Query("SELECT * FROM buildings")
    fun readAllWithFloors(): Flow<List<BuildingWithFloors>>
}

