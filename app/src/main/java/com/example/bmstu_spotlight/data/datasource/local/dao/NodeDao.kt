package com.example.bmstu_spotlight.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(node: NodeEntity)

    @Delete(entity = NodeEntity::class)
    suspend fun delete(node: NodeEntity)

    @Query("SELECT * FROM nodes")
    fun readAll(): Flow<List<NodeEntity>>

}