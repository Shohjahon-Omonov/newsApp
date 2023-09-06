package uz.gita.mr_smart.newsexamapp.domain.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mr_smart.newsexamapp.app.App

object NewsClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://www.terabayt.uz")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}