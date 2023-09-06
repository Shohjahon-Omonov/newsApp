package uz.gita.mr_smart.newsexamapp.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableResource
import uz.gita.mr_smart.newsexamapp.R
import uz.gita.mr_smart.newsexamapp.data.ResponseCategory
import uz.gita.mr_smart.newsexamapp.databinding.ItemCategoryBinding
import uz.gita.mr_smart.newsexamapp.domain.Pref
import uz.gita.mr_smart.newsexamapp.myutill.logger

class CategoryAdapter(private val list: List<ResponseCategory>, private val id: Int): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private var clickItem = Pref.getId()
    private lateinit var clickCategory: (id: Int) -> Unit
    inner class CategoryHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCategoryBinding.bind(view)
        fun bind(){
            if (absoluteAdapterPosition == clickItem){
                binding.categoryButton.setBackgroundResource(R.drawable.item_category)
                binding.categoryButton.setTextColor(Color.WHITE)
            } else {
                binding.categoryButton.setBackgroundResource(R.drawable.bkg_search)
                binding.categoryButton.setTextColor(Color.BLACK)
            }
            "$list".logger()
            binding.categoryButton.text = list[absoluteAdapterPosition].name
            binding.categoryButton.setOnClickListener {
                notifyItemChanged(clickItem)
                clickItem = absoluteAdapterPosition
                Pref.setId(absoluteAdapterPosition)
                notifyItemChanged(absoluteAdapterPosition)
                clickCategory.invoke(absoluteAdapterPosition)
            }
        }
    }

    fun setClickListener(block: (id: Int) -> Unit){
        clickCategory = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind()
    }
}