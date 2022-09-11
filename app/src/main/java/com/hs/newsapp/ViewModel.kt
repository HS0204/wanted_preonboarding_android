package com.hs.newsapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.hs.newsapp.config.ApplicationClass
import com.hs.newsapp.network.NewsService
import com.hs.newsapp.model.Article
import com.hs.newsapp.model.Category
import com.hs.newsapp.repository.SavedArticleRepository
import com.hs.newsapp.ui.newsDetail.NewsDetailFragment
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
        _category.value = ""
        tryGetNewsList()

        val savedArticleDao = ApplicationClass.getDatabase(application).SavedArticleDao()
        repository = SavedArticleRepository(savedArticleDao)
        readAllData = repository.readAllData
    }

    fun tryGetNewsList() {
        viewModelScope.launch {
            _status.value = NewsListStatus.LOADING
            try {
                val response = NewsService.newsList.getNews(country = "kr", category = _category.value!!, apiKey = BuildConfig.NEWS_API_KEY)
                _articles.value = response.articles
                _status.value = NewsListStatus.DONE
            } catch (e: Exception) {
                _status.value = NewsListStatus.ERROR
                _articles.value = listOf()
            }
        }
    }

    fun onArticleClicked(article: Article) {
        _article.value = article
    }

    fun onCategoryClicked(category: Category) {
        _category.value = category.title
    }

    private fun addArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

    private fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteArticle(article)
        }
    }

    fun insertDataToDatabase() {
        inputCheck()

        val writer = _article.value!!.author
        val content = _article.value!!.content
        val description = _article.value!!.description
        val publishedAt = _article.value!!.publishedAt
        val title = _article.value!!.title
        val url = _article.value!!.url
        val urlToImage = _article.value!!.urlToImage

        val curArticle = Article(0, writer, content, description, publishedAt, title, url, urlToImage, "1")
        addArticle(curArticle)

        Toast.makeText(this.getApplication(), "기사 추가", Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck() {
        if (_article.value?.author.isNullOrEmpty()) {
            _article.value?.author = "-"
        }
        if (_article.value?.content.isNullOrEmpty()) {
            _article.value?.content = "-"
        }
        if (_article.value?.description.isNullOrEmpty()) {
            _article.value?.description = "-"
        }
        if (_article.value?.publishedAt.isNullOrEmpty()) {
            _article.value?.publishedAt = "-"
        }
        if (_article.value?.title.isNullOrEmpty()) {
            _article.value?.title = "-"
        }
        if (_article.value?.url.isNullOrEmpty()) {
            _article.value?.url = "-"
        }
        if (_article.value?.urlToImage.isNullOrEmpty()) {
            _article.value?.urlToImage = "-"
        }
    }

    fun deleteDataFromDatabase() {
        deleteArticle(_article.value!!)
        Toast.makeText(this.getApplication(), "기사 삭제", Toast.LENGTH_SHORT).show()
    }

}