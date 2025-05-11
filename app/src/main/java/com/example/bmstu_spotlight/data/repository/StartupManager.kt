package com.example.bmstu_spotlight.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class StartupManager(
    private val preferences: AppPreferencesManager,
    private val edgeJsonImporter: EdgeJsonImporter
) {
    suspend fun performInitialIfNeeded() {
        val alreadyImported = withContext(Dispatchers.IO){
            preferences.edgeImportedFlow.first()
        }
        if (!alreadyImported) {
            edgeJsonImporter.importAllFromJson()
            preferences.setEdgesImported(true)
        }
    }
}