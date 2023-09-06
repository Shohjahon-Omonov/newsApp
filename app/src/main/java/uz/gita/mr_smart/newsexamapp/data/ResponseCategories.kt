package uz.gita.mr_smart.newsexamapp.data

data class ResponseCategories(
    val id: Int?,
    val name: String?,
    val slug: String?,
    val child: List<ResponseCategory>
)
