package com.example.kfu_app.data.repositories

import com.example.kfu_app.data.network.api.FirebaseApi
import com.example.kfu_app.domain.repositories.InstitutesRepository
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseInstitutesRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseApi: FirebaseApi
) : InstitutesRepository {

        val db: CollectionReference = firebaseFirestore.collection("institutes")
    override suspend fun getInstitutes(): List<InstitutesItem> {
        return firebaseApi.getInstitutes()
    }

//        override suspend fun getInstitutesfromDB(): List<InstitutesItem> {
//
//            val list = getInstitutes()
//            return listOf(
//                InstitutesItem(
//                    "0",
//                    "https://elabuga.tatar/wp-content/uploads/2022/06/elabuzhskiy-institut-kfu-5.jpg",
//                    "Институты",
//                    "https://media.kpfu.ru/news/o-narodnom-ornamente-rasskazhut-na-nauchnoy-srede"
//                ),
//                InstitutesItem(
//                    "1",
//                    "https://visa-vip.ru/wp-content/uploads/2017/07/09.jpg",
//                    "Схема поступления",
//                    "https://visa-vip.ru/wp-content/uploads/2017/07/09.jpg"
//                ),
//                InstitutesItem(
//                    "2",
//                    "https://studyinrussia.ru/upload/iblock/d49/d49ddc9db9182db99d9a76cce09785c4.jpg",
//                    "О КФУ",
//                    "https://www.google.ru/url?sa=i&url=https%3A%2F%2Fmedia.kpfu.ru%2Fnews%2Fv-aprele-dlya-abiturientov-kfu-proydut-14-pryamykh-efirov-i-12-dney-otkrytykh-dverey&psig=AOvVaw3nQfF0Ipw0IfiTv6xrfsf_&ust=1685546191968000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLC58YGrnf8CFQAAAAAdAAAAABAJ"
//                ),
//                InstitutesItem(
//                    "3",
//                    "https://studyinrussia.ru/images/pages/life-in-russia/zahod_TORFL_Depositphotos.jpg",
//                    "Схема проведения приема",
//                    "https://studyinrussia.ru/images/pages/life-in-russia/zahod_TORFL_Depositphotos.jpg"
//                )
//            )
//
//        }
//
//    override suspend fun getInstitutesFromDB(): InstitutesList? {
//        val list: InstitutesList? = firebaseApi.getInstitutes()
//        return list
//    }


}