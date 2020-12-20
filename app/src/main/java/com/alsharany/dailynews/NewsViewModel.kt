package com.alsharany.dailynews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alsharany.dailynews.models.News

class NewsViewModel : ViewModel() {
    val newsRepositry = NewsRepositry()
    private val mutableQueryTerm = MutableLiveData<Int>()


    var newsLiveData: LiveData<List<News>>
   // var newsByCategoryLiveData: LiveData<List<News>>
    // val typssLiveData: String?

    init {

       if(MainActivity.categoryId==0)
        newsLiveData = newsRepositry.fetchNews()
        else
           newsLiveData = newsRepositry.fetchNewsByCategory(MainActivity.categoryId)



    }
}