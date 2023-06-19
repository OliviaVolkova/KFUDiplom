package com.example.kfu_app.domain.repositories

import com.example.kfu_app.domain.model.FeatureConfiguration
import kotlinx.coroutines.flow.Flow

interface FeatureConfigurationRepository {
    fun observeConfiguration(): Flow<List<FeatureConfiguration>>
}