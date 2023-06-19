package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.fragments.entering.Entering

interface EnteringRepository {
    suspend fun getImages(): List<Entering>
}