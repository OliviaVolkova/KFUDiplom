package com.example.kfu_app.presentation.menu

data class MenuItem(
    val id: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val link: String = "",
    val type: ItemType
)

enum class ItemType{
    INSTITUTES, ENTERING, ABOUT, CATCHING
}