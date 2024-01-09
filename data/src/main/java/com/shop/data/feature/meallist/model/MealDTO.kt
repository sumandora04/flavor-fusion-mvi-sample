package com.shop.data.feature.meallist.model


import com.google.gson.annotations.SerializedName

data class MealDTO(
    @SerializedName("idMeal")
    val mealId: String,
    @SerializedName("strMeal")
    val meal: String,
    @SerializedName("strMealThumb")
    val mealThumb: String
)