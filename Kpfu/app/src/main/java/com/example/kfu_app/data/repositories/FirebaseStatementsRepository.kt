package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.repositories.NewsRepository
import com.example.kfu_app.domain.repositories.StatementsRepository
import com.example.kfu_app.presentation.fragments.navigationDrawer.statements.StatementItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseStatementsRepository  @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseApi: FirebaseApi
) : StatementsRepository {

    override suspend fun setStatement(statement: StatementItem) {
        firebaseApi.setStatement(statement)

    }

}