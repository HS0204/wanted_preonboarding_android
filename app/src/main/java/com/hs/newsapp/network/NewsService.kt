package com.hs.newsapp.network

import com.hs.newsapp.config.ApplicationClass

object NewsService {
    val newsList : NewsRetrofitInterface by lazy {
        ApplicationClass.sRetrofit.create(NewsRetrofitInterface::class.java)
    }
}
