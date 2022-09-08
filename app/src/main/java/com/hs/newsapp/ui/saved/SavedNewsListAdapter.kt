package com.hs.newsapp.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hs.newsapp.databinding.LayoutSavedNewsListBinding
import com.hs.newsapp.model.SavedArticle

class SavedNewsListAdapter(val clickListener: SavedArticleListener) :
        ListAdapter<SavedArticle, SavedNewsListAdapter.NewsListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsListAdapter.NewsListViewHolder {
        return NewsListViewHolder(LayoutSavedNewsListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SavedNewsListAdapter.NewsListViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(clickListener, article)
    }

    class NewsListViewHolder(private var binding: LayoutSavedNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: SavedArticleListener, newsArticle: SavedArticle) {
            binding.article = newsArticle
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SavedArticle>() {
        override fun areItemsTheSame(oldItem: SavedArticle, newItem: SavedArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: SavedArticle, newItem: SavedArticle): Boolean {
            return oldItem.url == newItem.url
        }
    }

}

class SavedArticleListener(val clickListener: (article: SavedArticle) -> Unit) {
    fun onClick(article: SavedArticle) = clickListener(article)
}