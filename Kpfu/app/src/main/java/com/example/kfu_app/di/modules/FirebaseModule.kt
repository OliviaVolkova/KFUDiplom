package com.example.kfu_app.di.modules

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.data.network.api.FirebaseApiImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirebaseApi(firebaseFirestore: FirebaseFirestore): FirebaseApi =
        FirebaseApiImpl(firebaseFirestore)
}