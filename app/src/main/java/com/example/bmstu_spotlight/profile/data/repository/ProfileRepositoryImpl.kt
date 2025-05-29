package com.example.bmstu_spotlight.profile.data.repository

import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.profile.domain.model.UserProfile
import com.example.bmstu_spotlight.profile.domain.model.toDomain
import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(private val networkService: NetworkService) : ProfileRepository {
    override suspend fun getUserProfile(): UserProfile = withContext(Dispatchers.IO) {
        val response = networkService.getUserProfile()

        if (response.isSuccessful) {
            val userProfileDto = response.body()
            return@withContext userProfileDto?.toDomain()
                ?: throw Exception("Empty response body")
        } else {
            throw Exception("Ошибка получения данных: ${response.message()}")
        }
    }
}