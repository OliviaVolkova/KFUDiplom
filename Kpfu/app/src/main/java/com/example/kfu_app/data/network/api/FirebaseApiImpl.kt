package com.example.kfu_app.data.network.api

import android.util.Log
import com.example.kfu_app.data.model.MenuItemRemote
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesItem
import com.example.kfu_app.presentation.fragments.navigationDrawer.schedule.LessonItem

import com.example.kfu_app.presentation.fragments.navigationDrawer.statements.StatementItem
import com.example.kfu_app.presentation.menu.ItemType
import com.example.kfu_app.presentation.menu.MenuItem
import com.example.kfu_app.presentation.news.NewsItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.joda.time.DateTime
import java.sql.Statement
import java.time.DayOfWeek

class FirebaseApiImpl(
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseApi {
    private val usersRef = firebaseFirestore.collection("userInfo")
    private val institutes = firebaseFirestore.collection("institutes")
    private val menu = firebaseFirestore.collection("menuWidgets")
    private val news = firebaseFirestore.collection("news")
    private val statements = firebaseFirestore.collection("statements")
    private var lessons = firebaseFirestore.collection("lessons")

    override suspend fun addUserInfo(user: UserInfo) {
        Log.d("MYTAG", "UserInfo: $user")
        usersRef.document(user.id).set(user).addOnFailureListener {
            Log.d("MYTAG", "Error $it")
        }
    }

    override suspend fun getUserInfoById(id: String): UserInfo? {
        return usersRef.document(id).get().await().toObject(UserInfo::class.java)
    }

    override suspend fun updateUserInfo(user: UserInfo) {
        usersRef.document(user.id).set(user)
    }

    override suspend fun getInstitutes(): List<InstitutesItem> {
        return institutes.get().await().documents.map {
            it.toObject(InstitutesItem::class.java) ?: throw IllegalStateException("Incorrect item")
        }
    }

    override suspend fun getMenu(): List<MenuItem> {
        return menu.get().await().documents.map {
            it.toObject(MenuItemRemote::class.java) ?: throw IllegalStateException("Incorrect item")
        }.map { item ->
            MenuItem(
                type = ItemType.valueOf(item.type),
                id = item.id,
                title = item.title,
                link = item.link,
                imageUrl = item.imageUrl
            )
        }
    }

    override suspend fun getNews(): List<NewsItem> {
        return news.get().await().documents.map {
            it.toObject(NewsItem::class.java) ?: throw IllegalStateException("Incorrect item")
        }
    }


    override suspend fun setStatement(statement: StatementItem) {
        statements.add(statement)
    }

    override suspend fun getLessonsByDayOfWeek(dayOfTheWeek: Int): List<LessonItem> {
        return lessons.whereEqualTo("dayOfWeek", dayOfTheWeek).get().await().documents.map {
            it.toObject(LessonItem::class.java) ?: throw IllegalStateException("Incorrect item")
        }
    }
}