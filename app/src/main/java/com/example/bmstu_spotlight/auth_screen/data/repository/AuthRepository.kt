package com.example.bmstu_spotlight.auth_screen.data.repository

import com.example.bmstu_spotlight.auth_screen.data.model.LoginRequest
import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager

class AuthRepository(
    val networkService: NetworkService,
    val appPreferences: AppPreferencesManager
) {
    var badRequest = true
     suspend fun login(email: String, password: String) {
        val noToken = ""
        try {
            val response = networkService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                badRequest = false
                response.body()?.let {
                    appPreferences.saveAuthToken(it.token)
                } ?: appPreferences.saveAuthToken(noToken)
            } else {
                throw Exception("Login failed: ${response.errorBody().toString()}")
            }
        } catch(e: Exception) {
            print("Request threw an exception: $e")
        }
    }
}