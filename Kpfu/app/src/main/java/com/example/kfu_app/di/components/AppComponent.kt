package com.example.kfu_app.di.components

import android.app.Application
import com.example.kfu_app.di.modules.*
import com.example.kfu_app.presentation.di.ScreenComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AuthModule::class,
        FirebaseModule::class,
        RepoModule::class,
        UseCaseModule::class
    ]
)
@Singleton
interface AppComponent {

    fun getScreenComponent(): ScreenComponent.Factory


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}