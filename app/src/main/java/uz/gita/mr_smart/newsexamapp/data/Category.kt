package uz.gita.mr_smart.newsexamapp.data

data class Category(
    val names: List<ResponseCategory>
): ListItem {
    override fun getListItemType(): Int {
        return ListItem.Type.CATEGORY.ordinal
    }

}