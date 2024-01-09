package com.shop.data.feature.meallist.mapper

import com.shop.data.feature.meallist.model.MealsListDTO
import com.shop.domain.feature.meallist.model.Meal

fun MealsListDTO.toDomainMeals(): List<Meal> {
    return this.meals.map {
        Meal(
            it.mealId, it.meal, it.mealThumb
        )
    }
}