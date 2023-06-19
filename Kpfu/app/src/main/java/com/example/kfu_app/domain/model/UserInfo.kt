package com.example.kfu_app.domain.model

data class UserInfo(
    var id: String = "",
    var email: String = "",
    val name: String? = null,
    val surName: String? = null,
    val lastName: String? = null,
    val citizenship: String? = null,
    val dateOfBirth: String? = null,
    val institute: String? = null,
    val group: String? = null,
    val personalId: String? = null,
    val placeOfBirth: String? = null,
    val libraryCard: String? = null,
    val image: String? = null
)