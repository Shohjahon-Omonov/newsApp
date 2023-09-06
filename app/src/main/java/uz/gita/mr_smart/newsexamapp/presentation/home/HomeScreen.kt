package uz.gita.mr_smart.newsexamapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mr_smart.newsexamapp.R
import uz.gita.mr_smart.newsexamapp.adapter.HomeAdapter
import uz.gita.mr_smart.newsexamapp.data.Category
import uz.gita.mr_smart.newsexamapp.data.ListItem
import uz.gita.mr_smart.newsexamapp.data.ResponseNews
import uz.gita.mr_smart.newsexamapp.databinding.ScreenHomeBinding
import uz.gita.mr_smart.newsexamapp.domain.Pref
import uz.gita.mr_smart.newsexamapp.myutill.logger
import uz.gita.mr_smart.newsexamapp.myutill.toNews
import uz.gita.mr_smart.newsexamapp.presentation.home.viewmodel.HomeViewModel
import uz.gita.mr_smart.newsexamapp.presentation.home.viewmodel.impl.HomeViewModelImpl

class HomeScreen: Fragment(R.layout.screen_home) {
    private val model: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModelImpl::class.java] }
    private val binding by viewBinding<ScreenHomeBinding>()
    private lateinit var rc: RecyclerView
    private var id = 0
    private lateinit var adapter: HomeAdapter
    private val list = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getData()
        setRecycler()
        model.categoryLiveData.observe(viewLifecycleOwner, categoryObserver)
        model.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        model.getCategories()
        model.getNewsMassages((list[0] as Category).names[0].id!!, 10)
    }

    private fun setRecycler(){
        binding.apply {
            rc = recycler
            rc.layoutManager = LinearLayoutManager(requireContext())
            adapter = HomeAdapter(list, id)
            adapter.setCategoryClick {
                progress.visibility = View.VISIBLE
                val data = list[0] as Category
                list.clear()
                list.add(data)
                id = it
                model.getNewsMassages(data.names[Pref.getId()].id!!, 30)
            }
            adapter.setNewsClickListener {
                val bundle = Bundle()
                bundle.putParcelable("news", it)
                findNavController().navigate(R.id.action_homeScreen_to_newsScreen, bundle)
                list.clear()
            }
            rc.adapter = adapter
        }
    }

    private val categoryObserver = Observer<Category>{
        list.add(0, it)
        "$it".logger()
        adapter.notifyItemInserted(0)
    }

    private val newsObserver = Observer<List<ResponseNews>>{
        val data = list[0]
        list.clear()
        list.add(data)
        for (news in it){
            list.add(news.toNews())
        }
        adapter.notifyDataSetChanged()
        binding.progress.visibility = View.GONE
    }

}