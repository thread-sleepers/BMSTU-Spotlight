package com.example.bmstu_spotlight.auth_screen.data

import android.util.Log
import com.example.bmstu_spotlight.auth_screen.domain.repository.AuthRepository
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager

class FakeAuthRepository : AuthRepository {
    override suspend fun validateUser(username: String, password: String): Boolean {
        kotlinx.coroutines.delay(1000)
        return username == "mike" && password == "mike"
    }

    override suspend fun saveToken(token: String) {}
}

class AuthRepositoryImpl(val preferences: AppPreferencesManager): AuthRepository {
    override suspend fun validateUser(
        username: String,
        password: String
    ): Boolean {
        TODO()
    }

    override suspend fun saveToken(token: String) {
        preferences.setAuthenticated(token)
    }

}