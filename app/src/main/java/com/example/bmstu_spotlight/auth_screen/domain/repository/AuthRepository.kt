package com.example.bmstu_spotlight.auth_screen.domain.repository

interface AuthRepository {
    suspend fun validateUser(username: String, password: String) : Boolean
    suspend fun saveToken(token: String)
}