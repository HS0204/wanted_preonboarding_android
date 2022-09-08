package com.hs.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_article_table")
data class Article(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val title: String,
        val url: String,
        val urlToImage: String,
        val savedStatus: String
)