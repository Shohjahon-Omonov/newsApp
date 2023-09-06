package uz.gita.mr_smart.newsexamapp.domain.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseCategory
import uz.gita.mr_smart.newsexamapp.data.ResponseNews

interface NewsApi {
    @GET("/api.php?action=categories")
    fun getCategories(): Call<List<ResponseCategories>>

    @GET("/api.php?action=posts&first_update=1613122152&last_update=0")
    fun getNewsMassages(@Query("category") category: Int, @Query("limit") limit: Int): Call<List<ResponseNews>>
}