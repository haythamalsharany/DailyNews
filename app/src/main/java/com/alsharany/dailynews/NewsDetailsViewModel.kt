package com.alsharany.dailynews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alsharany.dailynews.models.News
import java.util.*

class NewsDetailsViewModel:ViewModel() {
    val newsRepositry=NewsRepositry()
    private val newsIdLiveData = MutableLiveData<Int>()
   var newsDetailsLiveData= Transformations.switchMap(newsIdLiveData) { newsId ->
       newsRepositry.fetchSingleNews(newsId)
   }

    fun loadNews(newsId:Int ) {
        newsIdLiveData.value = newsId
    }
}