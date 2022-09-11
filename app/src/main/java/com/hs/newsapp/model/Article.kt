package com.hs.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_article_table")
data class Article(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var author: String,
        var content: String,
        var description: String,
        var publishedAt: String,
        var title: String,
        var url: String,
        var urlToImage: String,
        val savedStatus: String
)