package com.example.kfu_app.di.modules

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.data.repositories.FirebaseEnteringRepository
import com.example.kfu_app.data.repositories.FirebaseUserRepository
import com.example.kfu_app.domain.repositories.EnteringRepository
import com.example.kfu_app.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        firebaseApi: FirebaseApi
    ): UserRepository =
        FirebaseUserRepository(firebaseApi)

    @Singleton
    @Provides
    fun provideEnteringRepository(
        firebaseEnteringRepository: FirebaseEnteringRepository
    ): EnteringRepository {
        return firebaseEnteringRepository
    }

//    @Singleton
//    @Provides
//    fun providerNewsRepository(
//        firebaseApi: FirebaseApi
//    ): NewsRepository = FirebaseNewsRepository(firebaseApi)
}