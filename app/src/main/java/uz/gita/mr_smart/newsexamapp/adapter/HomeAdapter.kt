package uz.gita.mr_smart.newsexamapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.mr_smart.newsexamapp.R
import uz.gita.mr_smart.newsexamapp.app.App
import uz.gita.mr_smart.newsexamapp.data.Category
import uz.gita.mr_smart.newsexamapp.data.ListItem
import uz.gita.mr_smart.newsexamapp.data.News
import uz.gita.mr_smart.newsexamapp.databinding.ItemCategoriesBinding
import uz.gita.mr_smart.newsexamapp.databinding.ItemNewsMassageBinding
import uz.gita.mr_smart.newsexamapp.myutill.logger
import uz.gita.mr_smart.newsexamapp.presentation.home.HomeScreen

class HomeAdapter(private val items: List<ListItem>, private val id: Int): RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var clickCategory : (Int) -> Unit
    private lateinit var newsClickListener: (News) -> Unit

    fun setCategoryClick(block: (Int) -> Unit){
        clickCategory = block
    }

    fun setNewsClickListener(block: (News) -> Unit){
        newsClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            ListItem.Type.CAROUSEL.ordinal -> {
                return ViewHolderCarousel(LayoutInflater.from(parent.context).inflate(R.layout.item_caroucel, parent, false))
            }
            ListItem.Type.CATEGORY.ordinal -> {
                return ViewHolderCategory(LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false), clickCategory, id)
            }
            ListItem.Type.NEWS.ordinal -> {
                return ViewHolderNews(LayoutInflater.from(parent.context).inflate(R.layout.item_news_massage, parent, false), newsClickListener)
            }
            else -> {
                return ViewHolderCarousel(LayoutInflater.from(parent.context).inflate(R.layout.item_caroucel, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getListItemType()
    }

    override fun getItemCount(): Int = items.size

}

abstract class  BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    abstract fun bind(item: ListItem)
}

class ViewHolderNews (itemView: View, private val click: (News) -> Unit): BaseViewHolder(itemView){

    val binding = ItemNewsMassageBinding.bind(itemView)

    override fun bind(item: ListItem) {
        val data = item as News
        binding.apply {
            title.text = data.title
            Glide.with(newsImage)
                .load(data.image)
                .into(newsImage)
            date.text = data.postModified
            author.text = data.categoryName
            root.setOnClickListener {
                click.invoke(data)
            }
        }
    }

}

class ViewHolderCarousel(itemView: View): BaseViewHolder(itemView){
    override fun bind(item: ListItem) {

    }

}

class ViewHolderCategory(itemView: View, private val click: (id: Int) -> Unit, private val id: Int): BaseViewHolder(itemView){
    val binding = ItemCategoriesBinding.bind(itemView)

    override fun bind(item: ListItem) {
        val data = item as Category
        "$data".logger()
        binding.apply {
            val adapter = CategoryAdapter(data.names, id)
            val rc = recyclerCategory
            adapter.setClickListener(click)
            rc.layoutManager = LinearLayoutManager(App.instance, LinearLayoutManager.HORIZONTAL, false)
            rc.adapter = adapter
        }
    }

}