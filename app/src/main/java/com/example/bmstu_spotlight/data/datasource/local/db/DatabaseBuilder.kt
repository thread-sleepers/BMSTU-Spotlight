package com.example.bmstu_spotlight.data.datasource.local.db

import android.content.Context
import androidx.room.Room
import com.example.bmstu_spotlight.data.datasource.local.dao.BuildingDao
import com.example.bmstu_spotlight.data.datasource.local.dao.FloorDao
import com.example.bmstu_spotlight.data.datasource.local.dao.NodeDao

class DatabaseBuilder(private val context: Context) {
    private val database: MyDatabase by lazy {
        Room.databaseBuilder(
            context = context,
            klass = MyDatabase::class.java,
            name = "my-database-filename"
        ).build()
    }
    fun provideBuildingDao(): BuildingDao = database.buildingDao()
    fun provideFloorDao(): FloorDao = database.floorDao()
    fun provideNodeDao(): NodeDao = database.nodeDao()
}
