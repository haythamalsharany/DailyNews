package com.alsharany.dailynews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alsharany.dailynews.models.Category
import com.alsharany.dailynews.models.News

class CategoryViewModel : ViewModel() {

    val categoryLiveData: LiveData<List<Category>>
        init {
            categoryLiveData = NewsRepositry().fetchNewsCategory()
    }
}