package uz.gita.mr_smart.newsexamapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
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
): ListItem, Parcelable {
    override fun getListItemType(): Int {
        return ListItem.Type.NEWS.ordinal
    }
}