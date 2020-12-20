package com.alsharany.dailynews.models

import com.google.gson.annotations.SerializedName

data class Type (
    @SerializedName("id")
    var typeId: Int = 0,
    @SerializedName("type_name")
   var typeName: String=""
)