package com.hs.newsapp.ui.newsList

import com.hs.newsapp.config.ApplicationClass

object NewsListService {
    val newsListService : NewsListRetrofitInterface by lazy {
        ApplicationClass.sRetrofit.create(NewsListRetrofitInterface::class.java)
    }
}
