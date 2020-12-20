package com.alsharany.dailynews.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var categoryId: Int = 0,
    @SerializedName("category_name")
    var categoryName: String ="",
    @SerializedName("type_id")
    var typeId: Int=0
)
