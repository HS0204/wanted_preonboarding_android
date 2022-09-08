package com.hs.newsapp.ui.saved

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentSavedNewsListBinding
import com.hs.newsapp.ui.RecyclerViewMargin
import com.hs.newsapp.ui.newsList.ArticleListener
import com.hs.newsapp.ui.newsList.NewsListAdapter

class SavedNewsListFragment : BaseFragment<FragmentSavedNewsListBinding>(FragmentSavedNewsListBinding::bind, R.layout.fragment_saved_news_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = sharedViewModel

        binding.newsListRecyclerView.addItemDecoration(RecyclerViewMargin())
        binding.newsListRecyclerView.adapter = NewsListAdapter(ArticleListener { article ->
            sharedViewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_savedNewsListFragment_to_newsDetailFragment)
        })
    }
}