package com.hs.newsapp.repository

import androidx.lifecycle.LiveData
import com.hs.newsapp.data.SavedArticleDao
import com.hs.newsapp.model.Article

class SavedArticleRepository(private val savedArticleDaO: SavedArticleDao) {

    val readAllData: LiveData<List<Article>> = savedArticleDaO.readAllData()

    suspend fun addArticle(article: Article) {
        savedArticleDaO.addArticle(article)
    }
}