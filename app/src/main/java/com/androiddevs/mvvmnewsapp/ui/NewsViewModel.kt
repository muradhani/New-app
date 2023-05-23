package com.androiddevs.mvvmnewsapp.ui

import android.app.DownloadManager.Query
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepo : NewsRepository
) :ViewModel(){
    val breakingNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse : NewsResponse? = null

    val searchNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse : NewsResponse? = null


    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countrycode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepo.getBreakingNews(countrycode,breakingNewsPage)
        breakingNews.postValue(handelBreakingNewsResponse(response))
    }
    fun searchNews(searchQuery: String)=viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepo.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handelsearchNewsResponse(response))
    }
    private fun handelBreakingNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null ){
                    breakingNewsResponse = resultResponse
                }else{
                    val oldArticle = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticle?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())

    }

    private fun handelsearchNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
               searchNewsPage++
                if(searchNewsResponse == null ){
                    searchNewsResponse = resultResponse
                }else{
                    val oldArticle = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticle?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())

    }
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepo.upsert(article)
    }
    fun getSavedNews()=newsRepo.getSavedNews()
    fun deleteNews(article:Article) = viewModelScope.launch {
        newsRepo.deleteArticle(article)
    }
}