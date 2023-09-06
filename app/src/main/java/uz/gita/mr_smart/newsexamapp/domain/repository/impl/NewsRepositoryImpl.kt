package uz.gita.mr_smart.newsexamapp.domain.repository.impl

import retrofit2.Call
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseNews
import uz.gita.mr_smart.newsexamapp.domain.network.NewsApi
import uz.gita.mr_smart.newsexamapp.domain.network.NewsClient
import uz.gita.mr_smart.newsexamapp.domain.repository.NewsRepository

class NewsRepositoryImpl: NewsRepository {

    private val api = NewsClient.retrofit.create(NewsApi::class.java)

    override fun getCategories(): Call<List<ResponseCategories>> = api.getCategories()

    override fun getNewsMassages(category: Int, limit: Int): Call<List<ResponseNews>> = api.getNewsMassages(category, limit)

}