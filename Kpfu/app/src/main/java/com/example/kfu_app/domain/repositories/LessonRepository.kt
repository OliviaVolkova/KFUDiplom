package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.fragments.navigationDrawer.schedule.LessonItem


interface LessonRepository {

    suspend fun getLessonsByDayOfWeek(dayOfTheWeek: Int): List<LessonItem>
}