package uz.gita.mr_smart.newsexamapp.myutill

import android.util.Log
import uz.gita.mr_smart.newsexamapp.data.Category
import uz.gita.mr_smart.newsexamapp.data.News
import uz.gita.mr_smart.newsexamapp.data.ResponseCategories
import uz.gita.mr_smart.newsexamapp.data.ResponseCategory
import uz.gita.mr_smart.newsexamapp.data.ResponseNews

fun String.logger() {
    Log.d("TTT", this)
}

fun ResponseCategories.toCategory(): Category {
    val categories = this
    val list = mutableListOf<ResponseCategory>()
    for (category in categories.child) {
        list.add(category)
    }
    return Category(list)
}

fun ResponseNews.toNews(): News {
    val data = this
    val news = News(
        data.id,
        data.title,
        data.excerpt,
        data.content,
        data.published,
        data.updated,
        data.postId,
        data.postModified,
        data.categoryId,
        data.categoryName,
        data.image,
        data.url,
        data.priority,
        data.order
    )
    return news
}