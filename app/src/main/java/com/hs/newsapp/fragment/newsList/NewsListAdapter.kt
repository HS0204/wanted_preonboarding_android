package com.hs.newsapp.fragment.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hs.newsapp.databinding.LayoutNewsListBinding
import com.hs.newsapp.model.Article

class NewsListAdapter : ListAdapter<Article, NewsListAdapter.NewsListViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.NewsListViewHolder {
        return NewsListViewHolder(LayoutNewsListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsListAdapter.NewsListViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class NewsListViewHolder(private var binding: LayoutNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsArticle: Article) {
            binding.article = newsArticle
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

    }

}