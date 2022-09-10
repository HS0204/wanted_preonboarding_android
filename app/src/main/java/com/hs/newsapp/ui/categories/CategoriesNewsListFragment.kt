package com.hs.newsapp.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hs.newsapp.Owner
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsListBinding
import com.hs.newsapp.ui.RecyclerViewMargin
import com.hs.newsapp.ui.newsList.ArticleListener
import com.hs.newsapp.ui.newsList.NewsListAdapter

class CategoriesNewsListFragment : BaseFragment<FragmentNewsListBinding>(FragmentNewsListBinding::bind, R.layout.fragment_news_list) {

    lateinit var owner: Owner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as Owner
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel.tryGetNewsList()
        sharedViewModel.category.value?.let { owner.setActionBarTitle(getString(R.string.categories_list_name, it)) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.newsListRecyclerView.addItemDecoration(RecyclerViewMargin(margin = 70))
        binding.newsListRecyclerView.adapter = NewsListAdapter(ArticleListener { article ->
            sharedViewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_categoriesNewsListFragment_to_newsDetailFragment)
        })
    }

}