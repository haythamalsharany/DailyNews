package com.alsharany.dailynews

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alsharany.dailynews.api.NewsApi

import com.alsharany.dailynews.models.Category
import com.alsharany.dailynews.models.News
import com.alsharany.dailynews.models.Type

import com.google.gson.stream.JsonReader
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Reader

class NewsRepositry {
var newsApi:NewsApi
    init {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.68.1:8080/acadimay_site/apis/"  )
            .build()
        newsApi = retrofit.create(NewsApi::class.java)
    }
    fun fetchNews(): MutableLiveData<List<News>>{
        return fetchNewsMetaData(newsApi.fetchNews())

    }
    fun fetchSingleNews(newsId:Int):LiveData<News>{
        val detailsResponseLiveData: MutableLiveData<News> = MutableLiveData()
        val newsDetailsPageRequest: Call<List<News>> = newsApi.fetchSingleNews(newsId)
        newsDetailsPageRequest.enqueue(object :Callback<List<News>>{
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                var newsDetailsResponse = response.body()
                detailsResponseLiveData.value= newsDetailsResponse?.get(0)
                    ?: News()
                Log.d("news Details", newsDetailsResponse?.get(0)?.details.toString())
            }

            override fun onFailure(call:Call<List<News>> , t: Throwable) {
                Log.d("news Details failed",t?.message)
            }

        })
        return detailsResponseLiveData
    }
    fun fetchNewsByCategory(CategoryId:Int): MutableLiveData<List<News>>{
        return fetchNewsMetaData(newsApi.fetchNewsByCategory(CategoryId))

    }
    fun fetchNewsMetaData(  newsHomePageRequest:Call<List<News>>):
            MutableLiveData<List<News>> {
        val responseLiveData: MutableLiveData<List<News>> = MutableLiveData()
       // val newsHomePageRequest: Call<List<News>> = newsApi.fetchNews()
             newsHomePageRequest.enqueue(object : Callback<List<News>> {
             override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                var newsResponse = response.body()
                var newsList = newsResponse
                    ?: mutableListOf()
                 Log.d(" news", newsResponse.toString())
                responseLiveData.value = newsList


            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.d(" news failed", t.message)
            }
        })
        return responseLiveData
    }
    fun fetchNewsCategory(): MutableLiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        val CategoryPageRequest: Call<List<Category>> = newsApi.fetchNewsCategory()
        CategoryPageRequest.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                var categoryList = response.body()
                    ?: mutableListOf()
                responseLiveData.value = categoryList
                Log.d("category", response.body().toString())
            }
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d("failed category", t.message)
            }
        })
        return responseLiveData
    }
    fun fetchNewsType():MutableLiveData<List<Type>>{
        val responseLiveData: MutableLiveData<List<Type>> = MutableLiveData()
        val TypeHomePageRequest: Call<List<Type>> = newsApi.fetchNewsType()
        var typeResponse:String?=""
        TypeHomePageRequest.enqueue(object : Callback<List<Type>> {
            override fun onResponse(call: Call<List<Type>>, response: Response<List<Type>>) {
                var typeList = response.body()
                    ?: mutableListOf()
                responseLiveData.value = typeList
                Log.e("type ", typeList[0].typeName.toString())
            }
            override fun onFailure(call: Call<List<Type>>, t: Throwable) {
                Log.e("type failed", t.message)
            }
        })
       return responseLiveData
    }
}

