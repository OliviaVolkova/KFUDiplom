package com.example.kfu_app.presentation.authorization

import androidx.lifecycle.ViewModel
import com.example.kfu_app.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    fun isUserAuthenticated(): Flow<Boolean> = userUseCase.isUserAuthenticated()

}