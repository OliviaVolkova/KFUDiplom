package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.news.NewsItem

interface NewsRepository {

    suspend fun getNews(): List<NewsItem>
}