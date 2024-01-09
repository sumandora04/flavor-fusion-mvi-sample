package com.shop.data.feature.meallist.model


import com.google.gson.annotations.SerializedName
import com.shop.data.feature.meallist.model.MealDTO

data class MealsListDTO(
    @SerializedName("meals")
    val meals: List<MealDTO>
)