package uz.gita.mr_smart.newsexamapp.data

interface ListItem {
    enum class Type(value: Int){
        CAROUSEL(0), CATEGORY(1), NEWS(2)
    }
    fun getListItemType(): Int
}