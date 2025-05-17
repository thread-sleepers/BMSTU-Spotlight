package com.example.bmstu_spotlight.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

object AppPreferencesKeys {
    val IS_AUTHENTICATED = stringPreferencesKey("authenticated")
}


class AppPreferencesManager(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    val isAuthenticated: Flow<Boolean> =  context.dataStore.data
        .map { prefs -> prefs[AppPreferencesKeys.IS_AUTHENTICATED]?.isNotEmpty() == true }

    suspend fun setAuthenticated(value: String ) = withContext(Dispatchers.IO) {
        context.dataStore.edit { prefs ->
            prefs[AppPreferencesKeys.IS_AUTHENTICATED] = value
        }
    }
}