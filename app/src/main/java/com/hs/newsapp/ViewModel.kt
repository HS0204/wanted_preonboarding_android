package com.hs.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hs.newsapp.ui.newsList.NewsListService
import com.hs.newsapp.model.Article
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    enum class NewsListStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<NewsListStatus>()
    val status: LiveData<NewsListStatus> = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        Log.d("test", "뷰모델 시작")
        tryGetNewsList()
    }

    private fun tryGetNewsList() {
        viewModelScope.launch {
            _status.value = NewsListStatus.LOADING
            try {
                Log.d("test", "성공")
                val response = NewsListService.newsListService.getNews(country = "kr", apiKey = BuildConfig.NEWS_API_KEY)
                _articles.value = response.articles
                Log.d("test", "${_articles.value}")
                _status.value = NewsListStatus.DONE
            } catch (e: Exception) {
                Log.d("test", "실패 이유 ${e}")
                _status.value = NewsListStatus.ERROR
                _articles.value = listOf()
            }
            Log.d("test", "끝")
        }


        /*
        NewsListService.newListService.getNews(country = "kr", apiKey = BuildConfig.NEWS_API_KEY ).enqueue(object : Callback<TopNews> {
            override fun onResponse(call: Call<TopNews>, response: Response<TopNews>) {
                val body = response.body()
                if (body != null) {
                    Log.d("Test", "성공")
                    _articles.value = body.articles
                    Log.d("Test", "${articles.value}")
                } else {
                    Log.d("Test", "body null")
                }
            }

            override fun onFailure(call: Call<TopNews>, t: Throwable) {
                Log.d("Test", "통신 오류 : ${t.message}")
            }

        })
         */
    }

}