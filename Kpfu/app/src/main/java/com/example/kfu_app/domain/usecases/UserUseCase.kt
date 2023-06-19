package com.example.kfu_app.domain.usecases

import com.example.kfu_app.domain.model.SignUpInfo
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.domain.repositories.AuthRepository
import com.example.kfu_app.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) {

    suspend fun updateUsername(name: String) {
        authRepository.getCurrentUser()?.let {
            updateUser(it.copy(
                surName = name
            ))
        } ?: throw IllegalStateException("Not authorized")
    }

    suspend fun signUp(user: SignUpInfo): Boolean {
        val id = authRepository.signUp(user.email, user.password)
        id?.let {
            with(user) {
                addUser(
                    UserInfo(
                        id = it,
                        email = email,
                        name = name,
                        surName = surName,
                        lastName = lastName,
                        citizenship = citizenship,
                        dateOfBirth = dateOfBirth,
                        institute = institute,
                        group = group,
                        personalId = personalId,
                        placeOfBirth = placeOfBirth,
                        libraryCard = libraryCard
                    )
                )
            }
            return true
        }
        return false
    }

    suspend fun signIn(email: String, password: String): Boolean {
        val id = authRepository.signIn(email, password)
        return id != null
    }


    suspend fun getCurrentUser(): UserInfo? = authRepository.getCurrentUser()

    suspend fun signOut() = authRepository.signOut()


    suspend fun getUserById(userId: String): UserInfo? = userRepository.getUserInfoById(userId)

    private suspend fun addUser(user: UserInfo) = userRepository.addUserInfo(user)

    private suspend fun updateUser(user: UserInfo) = userRepository.updateUserInfo(user)

    fun isUserAuthenticated(): Flow<Boolean> = authRepository.isUserAuthenticatedFlow()
}