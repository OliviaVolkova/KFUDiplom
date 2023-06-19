package com.example.kfu_app.di.modules

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.data.repositories.FeatureConfigurationRepositoryImpl
import com.example.kfu_app.data.repositories.FirebaseAuthRepository
import com.example.kfu_app.domain.repositories.AuthRepository
import com.example.kfu_app.domain.repositories.FeatureConfigurationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        firebaseAuth: FirebaseAuth,
        firebaseApi: FirebaseApi
    ): AuthRepository =
        FirebaseAuthRepository(firebaseAuth, firebaseApi)

    @Singleton
    @Provides
    fun provideFeatureConfigurationRepository(): FeatureConfigurationRepository {
        return FeatureConfigurationRepositoryImpl()
    }

}