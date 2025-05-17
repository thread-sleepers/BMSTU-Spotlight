package com.example.bmstu_spotlight.auth_screen.data

import com.example.bmstu_spotlight.auth_screen.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class FakeAuthRepository : AuthRepository {
    override suspend fun validateUser(username: String, password: String): Boolean {
        kotlinx.coroutines.delay(1000)
        return username == "mike" && password == "mike"
    }
}