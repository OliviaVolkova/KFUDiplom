package com.example.kfu_app.presentation.di

import androidx.lifecycle.ViewModel
import com.example.kfu_app.presentation.authorization.signin.SignInViewModel
import com.example.kfu_app.presentation.authorization.signup.SignUpViewModel
import com.example.kfu_app.presentation.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ScreenModule {
    @Binds
    @IntoMap
    @VMKey(ProfileViewModel::class)
    fun provideProfilePetVM(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(SignInViewModel::class)
    fun provideSignInVM(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(SignUpViewModel::class)
    fun provideSignUpVM(viewModel: SignUpViewModel): ViewModel

}