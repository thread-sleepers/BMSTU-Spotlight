package com.example.bmstu_spotlight.profile.data

import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import com.example.bmstu_spotlight.profile.domain.model.UserProfile

class ProfileRepositoryImpl: ProfileRepository {
    override suspend fun getUserProfile(): UserProfile {
        return UserProfile(
            name = "Mike Wazowski",
            email = "mikewazowski@bmstu.ru",
            avatarUrl = "https://static.wikia.nocookie.net/disney/images/c/c5/%D0%9C%D0%B0%D0%B9%D0%BA.png/revision/latest?cb=20151025165018&path-prefix=ru",
            accountType = "BMSTU Account",
            language = "English"
        )
    }
}