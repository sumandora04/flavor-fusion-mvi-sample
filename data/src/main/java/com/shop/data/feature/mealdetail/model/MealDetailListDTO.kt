package com.shop.data.feature.mealdetail.model


import com.google.gson.annotations.SerializedName
import com.shop.data.feature.mealdetail.model.MealDetailDTO

data class MealDetailListDTO(
    @SerializedName("meals")
    val meals: List<MealDetailDTO>
)