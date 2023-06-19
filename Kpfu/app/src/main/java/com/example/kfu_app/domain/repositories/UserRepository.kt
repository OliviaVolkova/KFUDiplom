package com.example.kfu_app.domain.repositories

import com.example.kfu_app.domain.model.UserInfo

interface UserRepository {
    suspend fun addUserInfo(user: UserInfo)
    suspend fun getUserInfoById(id: String): UserInfo?
    suspend fun updateUserInfo(user: UserInfo)
}