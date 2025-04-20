package com.example.bmstu_spotlight.profile.domain.repository

import com.example.bmstu_spotlight.profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getUserProfile(): UserProfile
}