package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.fragments.Institutes.InstitutesItem

interface InstitutesRepository {

    suspend fun getInstitutes(): List<InstitutesItem>
}