package com.example.kfu_app.data.repositories

import com.example.kfu_app.domain.model.Feature.*
import com.example.kfu_app.domain.model.FeatureConfiguration
import com.example.kfu_app.domain.repositories.FeatureConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FeatureConfigurationRepositoryImpl : FeatureConfigurationRepository {
    override fun observeConfiguration(): Flow<List<FeatureConfiguration>> = MutableStateFlow(
        listOf(
            FeatureConfiguration(true, About),
            FeatureConfiguration(true, Statements),
            FeatureConfiguration(true, Schedule),
            FeatureConfiguration(true, Contacts),
            FeatureConfiguration(true, Institutes),
            FeatureConfiguration(true, Entering),
            FeatureConfiguration(true, Catching),
            FeatureConfiguration(true, Profile),
            FeatureConfiguration(true, News),
        )
    )
}