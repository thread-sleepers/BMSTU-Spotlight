package com.example.bmstu_spotlight.auth_screen.data.repository

import com.example.bmstu_spotlight.auth_screen.data.model.LoginRequest
import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager

class AuthRepository(
    val networkService: NetworkService,
    val appPreferences: AppPreferencesManager
) {
     suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val response = networkService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                response.body()?.let {
                    appPreferences.saveAuthToken(it.token)
                    Result.success(Unit)
                } ?: Result.failure(Exception("Empty token!"))
            } else {
                Result.failure(Exception("HTTP ${response.code()}: ${response.errorBody()?.string()}"))
            }
        } catch(e: Exception) {
            print("Request threw an exception: $e")
            Result.failure(e)
        }
    }
}