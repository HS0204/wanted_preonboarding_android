package com.hs.newsapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hs.newsapp.config.ApplicationClass
import com.hs.newsapp.ui.newsList.NewsListService
import com.hs.newsapp.model.Article
import com.hs.newsapp.model.Category
import com.hs.newsapp.repository.SavedArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    enum class NewsListStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<NewsListStatus>()
    val status: LiveData<NewsListStatus> = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    val readAllData: LiveData<List<Article>>
    private val repository: SavedArticleRepository

    init {
        Log.d("test", "뷰모델 시작")
        _category.value = "general"
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
                val response = NewsListService.newsListService.getNews(country = "kr", category = _category.value!!, apiKey = BuildConfig.NEWS_API_KEY)
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

    fun onCategoryClicked(category: Category) {
        _category.value = category.title
    }

    fun addArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

    fun insertDataToDatabase() {
        val writer = _article.value?.author
        val content = _article.value!!.content
        val description = _article.value!!.description
        val publishedAt = _article.value!!.publishedAt
        val title = _article.value!!.title
        val url = _article.value!!.url
        val urlToImage = _article.value!!.urlToImage

        /** !!!!!!!!!NULL 체크 완벽하지 않음!!!!!!! **/
        if (writer == null) {
            val curArticle = Article(0, "-", content, description, publishedAt, title, url, urlToImage, "1")
            addArticle(curArticle)
        } else {
            val curArticle = Article(0, writer, content, description, publishedAt, title, url, urlToImage, "1")
            addArticle(curArticle)
        }
    }

}