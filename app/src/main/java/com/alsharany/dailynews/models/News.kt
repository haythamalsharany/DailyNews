package com.alsharany.dailynews.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class News(
    @SerializedName("id")
    var newsId: Int = 0,
    @SerializedName("news_title")
    var title: String = "",
    @SerializedName("news_content")
    var details: String = "",
    @SerializedName("news_date")
    var date: Date = Date(),
    @SerializedName("news_image")
    var image: String = "",
    @SerializedName("category_id")
    var categoryId: Int = 0,
)