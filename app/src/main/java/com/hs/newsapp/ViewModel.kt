package com.hs.newsapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hs.newsapp.config.ApplicationClass
import com.hs.newsapp.ui.newsList.NewsListService
import com.hs.newsapp.model.Article
import com.hs.newsapp.model.SavedArticle
import com.hs.newsapp.remository.SavedArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    enum class NewsListStatus { LOADING, ERROR, DONE }

    /**
     News List
     **/
    private val _status = MutableLiveData<NewsListStatus>()
    val status: LiveData<NewsListStatus> = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    private val _savedArticle = MutableLiveData<SavedArticle>()
    val savedArticle: LiveData<SavedArticle> = _savedArticle

    /**
     Saved News List
     **/
    val readAllData: LiveData<List<SavedArticle>>
    private val repository: SavedArticleRepository

    init {
        Log.d("test", "뷰모델 시작")
        tryGetNewsList()

        val savedArticleDao = ApplicationClass.getDatabase(application).SavedArticleDao()
        repository = SavedArticleRepository(savedArticleDao)
        readAllData = repository.readAllData
    }

    private fun tryGetNewsList() {
        viewModelScope.launch {
            _status.value = NewsListStatus.LOADING
            try {
                Log.d("test", "성공")
                val response = NewsListService.newsListService.getNews(country = "kr", apiKey = BuildConfig.NEWS_API_KEY)
                _articles.value = response.articles
                _status.value = NewsListStatus.DONE
            } catch (e: Exception) {
                Log.d("test", "실패 이유 ${e}")
                _status.value = NewsListStatus.ERROR
                _articles.value = listOf()
            }
            Log.d("test", "끝")
        }
    }

    fun onArticleClicked(article: Article) {
        _article.value = article
    }

    fun onSavedArticleClicked(article: SavedArticle) {
        _savedArticle.value = article
    }

    fun addArticle(article: SavedArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

}