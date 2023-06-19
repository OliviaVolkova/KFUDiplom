package com.example.kfu_app.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val userLiveData: MutableLiveData<UserInfo?> = MutableLiveData()

    private val errorFlow = MutableSharedFlow<Throwable>(replay = 1)
    fun getError(): Flow<Throwable> = errorFlow

    fun signOut() {
        viewModelScope.launch(coroutineContext) {
            userUseCase.signOut()
            getCurrentUser()
        }
    }

    fun getCurrentUser(): LiveData<UserInfo?> {
        viewModelScope.launch(coroutineContext) {
            userLiveData.postValue(userUseCase.getCurrentUser())
        }
        return userLiveData
    }

    fun updateName(name: String) {
        viewModelScope.launch(coroutineContext) {
            userUseCase.updateUsername(name)
        }
    }
}