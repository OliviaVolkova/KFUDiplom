package com.example.kfu_app.presentation.authorization.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kfu_app.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val errorSignInFlow: MutableSharedFlow<Throwable> = MutableSharedFlow(replay = 1)
    fun observeSignInError(): Flow<Throwable> = errorSignInFlow

    fun signIn(email: String, password: String) {
        viewModelScope.launch(coroutineContext) {
            try {
                val user = userUseCase.signIn(email, password)
                Log.d("MYTAG", "UserViewModel signUp() : ${user}")
                  //TODO EXCEPTION EXT
            } catch (e: Exception) {
                e.printStackTrace()
                errorSignInFlow.emit(e)
            }
        }
    }
}