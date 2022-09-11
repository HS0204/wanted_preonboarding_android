package com.hs.newsapp.config

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hs.newsapp.R
import com.hs.newsapp.data.SavedArticleDatabase
import com.hs.newsapp.model.Category
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    val API_URL = "https://newsapi.org/"

    companion object {
        lateinit var sRetrofit: Retrofit
        lateinit var cateList: ArrayList<Category>

        @Volatile
        var DB_INSTANCE: SavedArticleDatabase? = null

        fun getDatabase(context: Context): SavedArticleDatabase {
            val tempInstance = DB_INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SavedArticleDatabase::class.java,
                        "saved_article_database"
                ).build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofitInstance()
        initializeCateData()
    }

    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        sRetrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initializeCateData() {

        cateList = arrayListOf<Category>()

        val img = arrayOf(
                R.drawable.ic_baseline_business_center_24, R.drawable.ic_baseline_library_music_24,
                R.drawable.ic_baseline_people_24, R.drawable.ic_baseline_local_hospital_24,
                R.drawable.ic_baseline_science_24, R.drawable.ic_baseline_sports_soccer_24,
                R.drawable.ic_baseline_biotech_24
        )

        val title = arrayOf(
                "business", "entertainment", "general", "health",
                "science", "sports", "technology"
        )

        for (i in img.indices) {
            val data = Category(img[i], title[i])
            cateList.add(data)
        }
    }
}