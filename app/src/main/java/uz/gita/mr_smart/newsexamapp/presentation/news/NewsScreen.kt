package uz.gita.mr_smart.newsexamapp.presentation.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Base64
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.mr_smart.newsexamapp.R
import uz.gita.mr_smart.newsexamapp.data.News
import uz.gita.mr_smart.newsexamapp.databinding.ScreenNewsBinding
import uz.gita.mr_smart.newsexamapp.presentation.news.viewmodel.NewsViewModel
import uz.gita.mr_smart.newsexamapp.presentation.news.viewmodel.impl.NewsViewModelImpl

class NewsScreen: Fragment(R.layout.screen_news) {
    private val model: NewsViewModel by lazy { ViewModelProvider(this)[NewsViewModelImpl::class.java] }
    private val binding by viewBinding<ScreenNewsBinding>()
    private lateinit var news: News

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news = arguments?.getParcelable("news", News::class.java)!!
        binding.apply {
            val encode = Base64.encodeToString(news.content?.toByteArray(), Base64.NO_PADDING)
//            vebView.loadData(encode, "text/html", "base64")
            vebView.text = Html.fromHtml(news.content, Html.FROM_HTML_MODE_COMPACT)
            Glide.with(image)
                .load(news.image)
                .into(image)
            title.text = news.title
            date.text = news.postModified
            auther.text = news.categoryName
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}