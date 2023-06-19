package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.repositories.NewsRepository
import com.example.kfu_app.presentation.news.NewsItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseApi: FirebaseApi
) : NewsRepository {

    val db: CollectionReference = firebaseFirestore.collection("news")

    override suspend fun getNews(): List<NewsItem> {
        return firebaseApi.getNews()
//        return listOf(
//            NewsItem(
//                "В Госдуме РФ представлена образовательная программа КФУ по оказанию первой помощи",
//                "https://media.kpfu.ru/sites/default/files/2023-05/IMG_8248.jpg",
//                "0",
//                "https://media.kpfu.ru/news/o-narodnom-ornamente-rasskazhut-na-nauchnoy-srede"
//            ),
//            NewsItem(
//                "В июне в КФУ продолжатся дни открытых дверей",
//                "https://media.kpfu.ru/sites/default/files/2023-05/IMG_2134-2_0.jpg",
//                "1",
//                "https://media.kpfu.ru/news/o-narodnom-ornamente-rasskazhut-na-nauchnoy-srede"
//            )
//        )


    }


}