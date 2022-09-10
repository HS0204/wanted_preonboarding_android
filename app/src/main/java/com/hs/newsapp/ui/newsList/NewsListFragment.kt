package com.hs.newsapp.ui.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsListBinding
import com.hs.newsapp.ui.RecyclerViewMargin

class NewsListFragment : BaseFragment<FragmentNewsListBinding>(FragmentNewsListBinding::bind, R.layout.fragment_news_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.newsListRecyclerView.addItemDecoration(RecyclerViewMargin(margin = 70))
        binding.newsListRecyclerView.adapter = NewsListAdapter(ArticleListener { article ->
            sharedViewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
        })
    }

}