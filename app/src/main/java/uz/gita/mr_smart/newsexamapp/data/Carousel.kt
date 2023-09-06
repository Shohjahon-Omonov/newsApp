package uz.gita.mr_smart.newsexamapp.data

import com.google.gson.annotations.SerializedName

data class Carousel(
    val id: Int?,
    val title: String?,
    val excerpt: String?,
    val content: String?,
    val published: Int?,
    val updated: Int?,
    val postId: String?,
    val postModified: String?,
    val categoryId: Int?,
    val categoryName: String?,
    val image: String?,
    val url: String?,
    val priority: String?,
    val order: String
): ListItem{
    override fun getListItemType(): Int {
        return ListItem.Type.CAROUSEL.ordinal
    }
}
