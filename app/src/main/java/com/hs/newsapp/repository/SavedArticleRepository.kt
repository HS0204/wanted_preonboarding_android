package com.hs.newsapp.repository

import androidx.lifecycle.LiveData
import com.hs.newsapp.data.SavedArticleDao
import com.hs.newsapp.model.SavedArticle

class SavedArticleRepository(private val savedArticleDaO: SavedArticleDao) {

    val readAllData: LiveData<List<SavedArticle>> = savedArticleDaO.readAllData()

    suspend fun addArticle(article: SavedArticle) {
        savedArticleDaO.addArticle(article)
    }
}