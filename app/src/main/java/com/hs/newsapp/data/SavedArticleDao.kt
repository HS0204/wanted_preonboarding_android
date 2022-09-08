package com.hs.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hs.newsapp.model.Article

@Dao
interface SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticle(article: Article)

    @Query("SELECT * FROM saved_article_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Article>>

}