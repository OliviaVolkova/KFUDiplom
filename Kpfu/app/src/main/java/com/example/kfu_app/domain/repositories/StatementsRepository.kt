package com.example.kfu_app.domain.repositories

import com.example.kfu_app.presentation.fragments.navigationDrawer.statements.StatementItem

interface StatementsRepository {

    suspend fun setStatement(statement: StatementItem)
}