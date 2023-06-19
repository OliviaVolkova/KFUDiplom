package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.repositories.LessonRepository
import com.example.kfu_app.presentation.fragments.navigationDrawer.schedule.LessonItem

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseLessonRepository @Inject constructor(
    private val firebaseApi: FirebaseApi
) : LessonRepository {

    override suspend fun getLessonsByDayOfWeek(dayOfTheWeek: Int): List<LessonItem> {
        return firebaseApi.getLessonsByDayOfWeek(dayOfTheWeek).sortedBy {
            val time = it.dateStart.split(":")
            time[0].toInt() * 60 + time[1].toInt()
        }
    }

}