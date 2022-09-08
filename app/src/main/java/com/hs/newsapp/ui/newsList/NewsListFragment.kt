package com.hs.newsapp.ui.newsList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hs.newsapp.R
import com.hs.newsapp.ViewModel
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : BaseFragment<FragmentNewsListBinding>(FragmentNewsListBinding::bind, R.layout.fragment_news_list) {

    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.newsListRecyclerView.adapter = NewsListAdapter()
    }

}