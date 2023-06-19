package com.example.kfu_app.presentation.authorization.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kfu_app.domain.model.SignUpInfo
import com.example.kfu_app.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignUpViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val errorSignUpFlow: MutableSharedFlow<Throwable> = MutableSharedFlow(replay = 1)
    fun getSignUpErrorFlow(): Flow<Throwable> = errorSignUpFlow

    fun signUp(email: String, password: String) {
        viewModelScope.launch(coroutineContext) {
            try {
                val user = userUseCase.signUp(
                    SignUpInfo(
                        email = email,
                        password = password,
                        name = "Оливия",
                        lastName = "Александровна",
                        surName = "Волкова",
                        citizenship = "Российская Федерация",
                        libraryCard = "19-8711",
                        personalId = "1198711",
                        dateOfBirth = "19.09.2001",
                        placeOfBirth = "Казань",
                        institute = "ИТИС",
                        group = "11-902",
                        image = "https://astrafarm.com/images/encyclopedia/ittenVes170221.jpg"
                    )
                )
                Log.d("MYTAG", "UserViewModel signUp() : ${user}")
                //TODO EXCEPTION EXT
                //errorLiveData.postValue(Throwable("User not found"))
            } catch (e: Exception) {
                e.printStackTrace()
                errorSignUpFlow.emit(e)
            }
        }
    }
}