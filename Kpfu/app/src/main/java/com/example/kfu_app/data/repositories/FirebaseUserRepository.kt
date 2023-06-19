package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.domain.repositories.UserRepository

class FirebaseUserRepository(
    private val firebaseApi: FirebaseApi,
) : UserRepository {

    override suspend fun getUserInfoById(id: String): UserInfo? {
        return firebaseApi.getUserInfoById(id)
    }

    override suspend fun addUserInfo(user: UserInfo) {
        firebaseApi.addUserInfo(user)
    }

    override suspend fun updateUserInfo(user: UserInfo) {
        firebaseApi.updateUserInfo(user)
    }

}