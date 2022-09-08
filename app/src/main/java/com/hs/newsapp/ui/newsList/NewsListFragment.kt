package com.hs.newsapp.ui.newsList

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : BaseFragment<FragmentNewsListBinding>(FragmentNewsListBinding::bind, R.layout.fragment_news_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.newsListRecyclerView.adapter = NewsListAdapter(ArticleListener { article ->
            sharedViewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
        })
    }

}