package com.example.kfu_app.di.modules

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application) = application.applicationContext

    @Singleton
    @Provides
    fun provideFusedLocationClient(context: Context): FusedLocationProviderClient =
        FusedLocationProviderClient(context)

    @Singleton()
    @Provides()
    fun provideCoroutineContext(): CoroutineContext = SupervisorJob() + Dispatchers.IO
}