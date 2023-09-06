package uz.gita.mr_smart.newsexamapp.data

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    val id: Int?,
    val title: String?,
    val excerpt: String?,
    val content: String?,
    @SerializedName("published_at")
    val published: Int?,
    @SerializedName("updated_at")
    val updated: Int?,
    @SerializedName("post_id")
    val postId: String?,
    @SerializedName("post_modified")
    val postModified: String?,
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("category_name")
    val categoryName: String?,
    val image: String?,
    val url: String?,
    val priority: String?,
    val order: String
)
