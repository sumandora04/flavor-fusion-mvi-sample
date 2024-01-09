package com.shop.data.feature.mealcategory.model


import com.google.gson.annotations.SerializedName

data class MealCategoryDetailDTO(
    @SerializedName("idCategory")
    val categoryId: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strCategoryDescription")
    val categoryDescription: String,
    @SerializedName("strCategoryThumb")
    val categoryThumb: String
)