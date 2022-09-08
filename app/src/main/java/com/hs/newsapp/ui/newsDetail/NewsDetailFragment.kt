package com.hs.newsapp.ui.newsDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hs.newsapp.Owner
import com.hs.newsapp.R
import com.hs.newsapp.config.BaseFragment
import com.hs.newsapp.databinding.FragmentNewsDetailBinding
import com.hs.newsapp.model.SavedArticle


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

        binding.detailArticleSaveIcon.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val writer = sharedViewModel.article.value?.author
        val description = sharedViewModel.article.value!!.description
        val publishedAt = sharedViewModel.article.value!!.publishedAt
        val title = sharedViewModel.article.value!!.title
        val url = sharedViewModel.article.value!!.url
        val urlToImage = sharedViewModel.article.value!!.urlToImage

        if (writer == null) {
            val curArticle = SavedArticle(0, "-", description, publishedAt, title, url, urlToImage)
            sharedViewModel.addArticle(curArticle)
        } else {
            val curArticle = SavedArticle(0, writer, description, publishedAt, title, url, urlToImage)
            sharedViewModel.addArticle(curArticle)
        }

        Toast.makeText(this.requireContext(), "성공!", Toast.LENGTH_SHORT).show()
    }

}