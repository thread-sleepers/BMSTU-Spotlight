package com.example.bmstu_spotlight.profile.domain.model

data class UserProfile(
    val name: String,
    val email: String,
    val avatarUrl: String,
    val accountType: String,
    val language: String
)