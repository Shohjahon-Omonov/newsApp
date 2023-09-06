package uz.gita.mr_smart.newsexamapp.domain

import android.content.Context
import uz.gita.mr_smart.newsexamapp.app.App

object Pref {
    private val pref = App.instance.getSharedPreferences("id", Context.MODE_PRIVATE)
    fun setId(id: Int){
        pref.edit().putInt("id", id).apply()
    }
    fun getId(): Int = pref.getInt("id", 0)
}