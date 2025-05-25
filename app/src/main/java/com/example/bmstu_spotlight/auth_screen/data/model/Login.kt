package com.example.bmstu_spotlight.auth_screen.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("user_name")
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String
)