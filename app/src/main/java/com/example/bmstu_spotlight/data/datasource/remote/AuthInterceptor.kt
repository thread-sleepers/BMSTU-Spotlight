package com.example.bmstu_spotlight.data.datasource.remote

import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferencesManager: AppPreferencesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferencesManager.getAuthTokenSync()
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}