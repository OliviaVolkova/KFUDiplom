package com.example.kfu_app.presentation.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface VMFactoryModule {

    @Binds
    fun provideViewModelFactory(viewModelProviderFactory: VMProviderFactory): ViewModelProvider.Factory
}