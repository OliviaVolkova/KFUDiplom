package com.example.kfu_app.di.modules

import com.example.kfu_app.domain.repositories.AuthRepository
import com.example.kfu_app.domain.repositories.FeatureConfigurationRepository
import com.example.kfu_app.domain.repositories.UserRepository
import com.example.kfu_app.domain.usecases.FeatureUseCase
import com.example.kfu_app.domain.usecases.UserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository
    ): UserUseCase = UserUseCase(
        authRepository = authRepository,
        userRepository = userRepository
    )

    @Singleton
    @Provides
    fun providerFeatureUseCase(
        authRepository: AuthRepository,
        featureConfigurationRepository: FeatureConfigurationRepository
    ): FeatureUseCase {
        return FeatureUseCase(authRepository, featureConfigurationRepository)
    }
}