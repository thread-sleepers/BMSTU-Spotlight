package com.example.bmstu_spotlight.profile.data.model

import android.view.textclassifier.TextLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Url

@Serializable
data class UserProfileDto(
    @SerialName("name") val name: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("avatarUrl") val avatarUrl: String? = null,
    @SerialName("accountType") val accountType: String? = null,
    @SerialName("language") val language: String? = null
)