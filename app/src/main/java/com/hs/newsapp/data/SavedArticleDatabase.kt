package com.hs.newsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hs.newsapp.model.SavedArticle

@Database(entities = [SavedArticle::class], version = 1, exportSchema = false)
abstract class SavedArticleDatabase: RoomDatabase() {

    abstract fun SavedArticleDao(): SavedArticleDao

}