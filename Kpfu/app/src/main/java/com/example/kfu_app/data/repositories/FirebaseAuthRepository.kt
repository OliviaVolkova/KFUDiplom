package com.example.kfu_app.data.repositories

import android.util.Log
import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.domain.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val api: FirebaseApi
) : AuthRepository {

    private val isAuthenticated: MutableSharedFlow<Boolean> = MutableSharedFlow(1)
    private val currentUserFlow: MutableSharedFlow<UserInfo?> = MutableSharedFlow()

    init {
        observeAuthStateListener()
    }

    override suspend fun signUp(email: String, password: String): String? {
        auth.createUserWithEmailAndPassword(email, password).await()
        return auth.currentUser?.uid
    }


    private fun observeAuthStateListener() {
        auth.addAuthStateListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("AUTH", "$it ${it.currentUser} ${auth} ${auth.currentUser}")
                isAuthenticated.emit(auth.currentUser != null)
                currentUserFlow.emit(auth.currentUser?.let {
                    getUserFromFirebaseUser(it)
                })
            }
        }
    }

    override suspend fun signIn(email: String, password: String): String? {
        auth.signInWithEmailAndPassword(email, password).await()
        return auth.currentUser?.let {
            getUserFromFirebaseUser(it)?.id
        }
    }

    override suspend fun getCurrentUser(): UserInfo? = auth.currentUser?.let {
        getUserFromFirebaseUser(it)
    }

    private suspend fun getUserFromFirebaseUser(firebaseUser: FirebaseUser): UserInfo? =
        with(firebaseUser) {
            api.getUserInfoById(uid)
        }

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun isUserAuthenticated(): Boolean = auth.currentUser != null

    override fun isUserAuthenticatedFlow(): Flow<Boolean> = isAuthenticated.onStart {
        emit(auth.currentUser != null)
    }

    override fun currrentUserFlow(): Flow<UserInfo?> = currentUserFlow
}