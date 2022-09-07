package com.hs.newsapp.fragment.newsList

import com.hs.newsapp.config.ApplicationClass

object NewsListService {
    val newsListService : NewsListRetrofitInterface by lazy {
        ApplicationClass.sRetrofit.create(NewsListRetrofitInterface::class.java)
    }
}
