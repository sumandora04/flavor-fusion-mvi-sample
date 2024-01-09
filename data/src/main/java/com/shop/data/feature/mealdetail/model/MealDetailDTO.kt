package com.shop.data.feature.mealdetail.model


import com.google.gson.annotations.SerializedName

data class MealDetailDTO(
    @SerializedName("idMeal")
    val mealId: String,
    @SerializedName("strArea")
    val area: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strInstructions")
    val instructions: String,
    @SerializedName("strMeal")
    val meal: String,
    @SerializedName("strMealThumb")
    val mealThumb: String,
)