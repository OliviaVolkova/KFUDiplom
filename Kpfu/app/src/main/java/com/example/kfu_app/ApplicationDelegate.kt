package com.example.kfu_app

import android.app.Application
import android.util.Log
import android.view.WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
import com.example.kfu_app.di.components.AppComponent
import com.example.kfu_app.di.components.DaggerAppComponent
import com.example.kfu_app.presentation.gcm.MessagingService
import com.google.firebase.messaging.FirebaseMessaging

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MYTAG", "Application started")
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        test()
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("GCMTag", "Registration token ${it.result}")
        }
    }

    companion object {
        lateinit var component: AppComponent

        fun getScreenComponent() = component.getScreenComponent().create()
    }

    fun test(){
        val value = SOFT_INPUT_IS_FORWARD_NAVIGATION or SOFT_INPUT_STATE_ALWAYS_VISIBLE
        Log.d("TEST", "$value")
        Log.d("TEST", "${288 and android.view.WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION}")
    }
}