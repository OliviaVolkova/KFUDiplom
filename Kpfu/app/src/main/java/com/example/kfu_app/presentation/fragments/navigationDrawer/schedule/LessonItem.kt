package com.example.kfu_app.presentation.fragments.navigationDrawer.schedule

data class LessonItem(
    val id: String = "",
    val dateStart: String = "",
    val dateEnd: String = "",
    val name: String = "",
    val room: String = "",
    val institute: String = "",
    val groupNumber: String = "",
    val teacher: String = "",
    val dayOfTheWeek: Int = 0
)
