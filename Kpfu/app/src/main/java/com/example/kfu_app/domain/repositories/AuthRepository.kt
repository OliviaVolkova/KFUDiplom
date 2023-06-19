package com.example.kfu_app.domain.repositories

import com.example.kfu_app.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(email: String, password: String): String?
    suspend fun signIn(email: String, password: String): String?
    suspend fun getCurrentUser(): UserInfo?
    suspend fun signOut()
    fun isUserAuthenticated(): Boolean
    fun isUserAuthenticatedFlow(): Flow<Boolean>
    fun currrentUserFlow(): Flow<UserInfo?>
}