package com.example.kfu_app.data.network.api

import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesItem
import com.example.kfu_app.presentation.fragments.navigationDrawer.schedule.LessonItem
import com.example.kfu_app.presentation.fragments.navigationDrawer.statements.StatementItem
import com.example.kfu_app.presentation.menu.MenuItem
import com.example.kfu_app.presentation.news.NewsItem

interface FirebaseApi {
    suspend fun addUserInfo(user: UserInfo)
    suspend fun getUserInfoById(id: String): UserInfo?
    suspend fun updateUserInfo(user: UserInfo)
    suspend fun getInstitutes(): List<InstitutesItem>
    suspend fun getMenu(): List<MenuItem>
    suspend fun getNews(): List<NewsItem>

    suspend fun setStatement(statement: StatementItem)
    suspend fun getLessonsByDayOfWeek(dayOfWeek: Int): List<LessonItem>
}