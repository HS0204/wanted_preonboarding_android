package com.hs.newsapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hs.newsapp.ui.newsList.NewsListAdapter
import com.hs.newsapp.model.Article

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions().centerCrop())
                .into(imgView)
    }
}

@BindingAdapter("savedStatus")
fun bindIcon(imgView: ImageView, status: String?) {
    if (status == "1") {
        Glide.with(imgView.context)
                .load(R.drawable.ic_baseline_star_24)
                .apply(RequestOptions().centerCrop())
                .into(imgView)
    } else {
        Glide.with(imgView.context)
                .load(R.drawable.ic_baseline_star_outline_24)
                .apply(RequestOptions().centerCrop())
                .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsListAdapter
    adapter.submitList(data)
}