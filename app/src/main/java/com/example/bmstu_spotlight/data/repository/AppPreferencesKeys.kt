package com.example.bmstu_spotlight.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bmstu_spotlight.data.repository.AppPreferencesKeys.AUTH_TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AppPreferencesKeys {
    val EDGES_IMPORTED = booleanPreferencesKey("edges_imported")
    val AUTH_TOKEN = stringPreferencesKey("auth_token")
    }


class AppPreferencesManager(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    val authToken = context.dataStore.data.map { it[AUTH_TOKEN] }

    @Volatile
    private var currentToken: String? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            authToken.collect {
                currentToken = it
            }
        }
    }

    val edgeImportedFlow: Flow<Boolean> =  context.dataStore.data
        .map { prefs -> prefs[AppPreferencesKeys.EDGES_IMPORTED] == true }

    suspend fun setEdgesImported(value: Boolean) = withContext(Dispatchers.IO) {
        context.dataStore.edit { prefs ->
            prefs[AppPreferencesKeys.EDGES_IMPORTED] = value
        }
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    suspend fun clearAuthToken() = withContext(Dispatchers.IO) {
        context.dataStore.edit { references ->
            references.remove(AppPreferencesKeys.AUTH_TOKEN)
        }
    }

    fun getAuthTokenSync(): String? = currentToken

}

