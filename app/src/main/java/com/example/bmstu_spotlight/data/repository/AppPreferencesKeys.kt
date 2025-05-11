package com.example.bmstu_spotlight.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

object AppPreferencesKeys {
    val EDGES_IMPORTED = booleanPreferencesKey("edges_imported")
    }


class AppPreferencesManager(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    val edgeImportedFlow: Flow<Boolean> =  context.dataStore.data
        .map { prefs -> prefs[AppPreferencesKeys.EDGES_IMPORTED] == true }

    suspend fun setEdgesImported(value: Boolean) = withContext(Dispatchers.IO) {
        context.dataStore.edit { prefs ->
            prefs[AppPreferencesKeys.EDGES_IMPORTED] = value
        }
    }
}