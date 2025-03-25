package com.example.bmstu_spotlight.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.bmstu_spotlight.data.datasource.local.entities.FloorEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.FloorWithNodes
import kotlinx.coroutines.flow.Flow

@Dao
interface FloorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(floor: FloorEntity)

    @Delete(entity = FloorEntity::class)
    suspend fun delete(floor: FloorEntity)

    @Query("SELECT * FROM floors")
    fun readAll(): Flow<List<FloorEntity>>

    @Transaction
    @Query("SELECT * FROM floors")
    fun readAllWithNodes(): Flow<List<FloorWithNodes>>
}
