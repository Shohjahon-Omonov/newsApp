package uz.gita.mr_smart.newsexamapp.presentation.home.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.mr_smart.newsexamapp.data.Category
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseNews
import uz.gita.mr_smart.newsexamapp.domain.repository.NewsRepository
import uz.gita.mr_smart.newsexamapp.domain.repository.impl.NewsRepositoryImpl
import uz.gita.mr_smart.newsexamapp.myutill.logger
import uz.gita.mr_smart.newsexamapp.myutill.toCategory
import uz.gita.mr_smart.newsexamapp.presentation.home.viewmodel.HomeViewModel

class HomeViewModelImpl: HomeViewModel, ViewModel() {

    val repository: NewsRepository = NewsRepositoryImpl()

    override val categoryLiveData = MutableLiveData<Category>()
    override val newsLiveData = MutableLiveData<List<ResponseNews>>()


    override fun getCategories(){

        repository.getCategories().enqueue(object : Callback<List<ResponseCategories>>{
            override fun onResponse(
                call: Call<List<ResponseCategories>>,
                response: Response<List<ResponseCategories>>
            ) {
                if (response.isSuccessful && response.body() != null){
                    val categories = response.body()!![0]
                    categoryLiveData.value = categories.toCategory()
                }
            }

            override fun onFailure(call: Call<List<ResponseCategories>>, t: Throwable) {

            }

        })
    }

    override fun getNewsMassages(category: Int, limit: Int) {
        repository.getNewsMassages(category, limit).enqueue(object : Callback<List<ResponseNews>>{
            override fun onResponse(
                call: Call<List<ResponseNews>>,
                response: Response<List<ResponseNews>>
            ) {
                if (response.isSuccessful && response.body() != null){
                    newsLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ResponseNews>>, t: Throwable) {

            }

        })
    }

}