package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.menu.MenuItem

interface MenuRepositiry {

    suspend fun getMenu(): List<MenuItem>
}