package com.hs.newsapp.ui.newsDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hs.newsapp.Owner
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsDetailBinding


class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(
        FragmentNewsDetailBinding::bind,
        R.layout.fragment_news_detail
) {

    lateinit var owner: Owner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as Owner
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel.article.value?.title?.let { owner.setActionBarTitle(it) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewmodel = sharedViewModel

    }

}