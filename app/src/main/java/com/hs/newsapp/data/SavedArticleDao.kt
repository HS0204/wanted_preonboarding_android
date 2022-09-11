package com.hs.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hs.newsapp.model.Article

@Dao
interface SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM saved_article_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Article>>

}