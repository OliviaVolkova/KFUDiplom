package com.example.kfu_app.domain.usecases

import com.example.kfu_app.domain.model.FeatureConfiguration
import com.example.kfu_app.domain.repositories.AuthRepository
import com.example.kfu_app.domain.repositories.FeatureConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class FeatureUseCase(
    private val authRepository: AuthRepository,
    private val featureConfigurationRepository: FeatureConfigurationRepository
) {
    fun observeConfiguration(): Flow<List<FeatureConfiguration>> {
        return authRepository.isUserAuthenticatedFlow().combine(
            featureConfigurationRepository.observeConfiguration()
        ) { isAuthorized, features ->
            features.map {
                it.copy(
                    isEnabled = (it.feature.requireAuth && isAuthorized) || !it.feature.requireAuth
                )
            }
        }
    }
}