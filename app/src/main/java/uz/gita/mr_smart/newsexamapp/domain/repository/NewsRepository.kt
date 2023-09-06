package uz.gita.mr_smart.newsexamapp.domain.repository

import retrofit2.Call
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseNews

interface NewsRepository {
    fun getCategories(): Call<List<ResponseCategories>>
    fun getNewsMassages(category: Int, limit: Int): Call<List<ResponseNews>>
}