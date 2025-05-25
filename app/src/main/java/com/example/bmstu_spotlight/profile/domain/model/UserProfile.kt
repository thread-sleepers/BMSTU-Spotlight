package com.example.bmstu_spotlight.profile.domain.model

import com.example.bmstu_spotlight.profile.data.model.UserProfileDto

data class UserProfile(
    val name: String,
    val email: String,
    val avatarUrl: String,
    val accountType: String,
    val language: String
)

fun UserProfileDto.toDomain() = UserProfile(
    name = this.name ?: "Unknown",
    email = this.email ?: "No email",
    avatarUrl = this.avatarUrl ?: "Unknown",
    accountType = this.accountType ?: "Unknown",
    language = this.language ?: "Unknown"
)