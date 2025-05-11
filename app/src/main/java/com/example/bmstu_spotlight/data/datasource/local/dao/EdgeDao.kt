package com.example.bmstu_spotlight.data.datasource.local.dao

import androidx.room.*
import com.example.bmstu_spotlight.data.datasource.local.entities.EdgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EdgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(edges: List<EdgeEntity>)

    @Query("SELECT * FROM edges")
    fun getAll(): Flow<List<EdgeEntity>>
}