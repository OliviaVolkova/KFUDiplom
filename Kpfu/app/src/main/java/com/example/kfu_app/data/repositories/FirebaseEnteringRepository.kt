package com.example.kfu_app.data.repositories

import com.example.kfu_app.domain.repositories.EnteringRepository
import com.example.kfu_app.presentation.fragments.entering.Entering
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseEnteringRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : EnteringRepository {

    val db: CollectionReference = firebaseFirestore.collection("entering")

    override suspend fun getImages(): List<Entering> {
        return listOf(
            Entering(
                "0",
                "https://admissions.kpfu.ru/sites/default/files/inline-images/Схема%20поступления_page-0001.jpg"
            ),
            Entering(
                "1",
                "https://admissions.kpfu.ru/sites/default/files/inline-images/Схема%20поступления_page-0002_0.jpg"
            )
        )
    }
}
