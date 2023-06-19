package com.example.kfu_app.domain.model

sealed class Feature(val requireAuth: Boolean = false) {
    object About : Feature()
    object Statements : Feature(true)
    object Schedule : Feature(true)
    object Contacts : Feature()
    object Institutes : Feature()
    object Entering : Feature()
    object Catching : Feature()
    object Profile : Feature(true)
    object News : Feature()
}
