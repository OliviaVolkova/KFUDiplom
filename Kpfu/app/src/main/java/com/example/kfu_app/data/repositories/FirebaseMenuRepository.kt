package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.repositories.MenuRepositiry
import com.example.kfu_app.presentation.menu.MenuItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseMenuRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseApi: FirebaseApi
) : MenuRepositiry {

    val db: CollectionReference = firebaseFirestore.collection("menu")

    override suspend fun getMenu(): List<MenuItem> {
        return firebaseApi.getMenu()

//        return listOf(
//            MenuItem(
//                "0",
//                "https://elabuga.tatar/wp-content/uploads/2022/06/elabuzhskiy-institut-kfu-5.jpg",
//                "Институты",
//                "https://elabuga.tatar/wp-content/uploads/2022/06/elabuzhskiy-institut-kfu-5.jpg",
//                ItemType.INSTITUTES
//            ),
//            MenuItem(
//                "1",
//                "https://visa-vip.ru/wp-content/uploads/2017/07/09.jpg",
//                "Схема поступления",
//                "https://static.tildacdn.com/tild3635-3661-4230-a334-383133346633/photo.jpg",
//                ItemType.ENTERING
//            ),
//            MenuItem(
//                "2",
//                "https://studyinrussia.ru/upload/iblock/d49/d49ddc9db9182db99d9a76cce09785c4.jpg",
//                "О КФУ",
//                "https://media.kpfu.ru/sites/default/files/2023-03/IMG_2544_0.jpg",
//                ItemType.ABOUT
//            ),
//            MenuItem(
//                "3",
//                "https://studyinrussia.ru/images/pages/life-in-russia/zahod_TORFL_Depositphotos.jpg",
//                "Схема проведения приема",
//                "https://studyinrussia.ru/images/pages/life-in-russia/zahod_TORFL_Depositphotos.jpg",
//                ItemType.CATCHING
//            )
//        )

    }
}