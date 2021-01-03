package com.alsharany.dailynews.api

import com.alsharany.dailynews.models.Category
import com.alsharany.dailynews.models.News
import com.alsharany.dailynews.models.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsApi {

    @GET("news_api.php")
    fun fetchNews(): Call<List<News>>
    @GET("news_api.php?")
    fun fetchSingleNews(@Query("newsId") query: Int): Call<List<News>>
    @GET("news_api.php?")
    fun fetchNewsByCategory(@Query("categoryId") query: Int): Call<List<News>>

    @GET("category_api.php?typeId=1")
    fun fetchNewsCategory(): Call<List<Category>>

    @GET("type_api.php")
    fun fetchNewsType(): Call<List<Type>>

}