package com.shop.data.feature.mealcategory.model


import com.google.gson.annotations.SerializedName
import com.shop.data.feature.mealcategory.model.MealCategoryDetailDTO

data class MealsCategoriesDTO(
    @SerializedName("categories")
    val categories: List<MealCategoryDetailDTO>
)