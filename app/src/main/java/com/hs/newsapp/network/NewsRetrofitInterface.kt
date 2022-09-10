package com.hs.newsapp.network

import com.hs.newsapp.model.TopNews
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRetrofitInterface {
    @GET("v2/top-headlines")
    suspend fun getNews(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("apiKey") apiKey: String
    ): TopNews
}
