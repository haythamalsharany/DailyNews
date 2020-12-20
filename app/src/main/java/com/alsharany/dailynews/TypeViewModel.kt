package com.alsharany.dailynews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alsharany.dailynews.models.Category
import com.alsharany.dailynews.models.Type

class TypeViewModel: ViewModel() {

    val typeLiveData: LiveData<List<Type>>
    init {
        typeLiveData = NewsRepositry().fetchNewsType()
    }
}