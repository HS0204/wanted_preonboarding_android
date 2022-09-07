package com.hs.newsapp.model

data class TopNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)