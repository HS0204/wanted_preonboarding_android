package com.hs.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hs.newsapp.model.SavedArticle

@Dao
interface SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticle(article: SavedArticle)

    @Query("SELECT * FROM saved_article_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<SavedArticle>>

}