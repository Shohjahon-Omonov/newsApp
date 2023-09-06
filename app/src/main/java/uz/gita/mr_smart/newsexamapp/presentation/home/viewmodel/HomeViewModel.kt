package uz.gita.mr_smart.newsexamapp.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.http.Query
import uz.gita.mr_smart.newsexamapp.data.Category
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseNews

interface HomeViewModel {

    val categoryLiveData: LiveData<Category>
    val newsLiveData: LiveData<List<ResponseNews>>

    fun getCategories()

    fun getNewsMassages(category: Int, limit: Int)
}