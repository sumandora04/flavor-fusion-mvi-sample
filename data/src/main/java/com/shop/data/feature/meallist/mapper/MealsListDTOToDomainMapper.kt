package com.shop.data.feature.meallist.mapper

import com.shop.data.feature.meallist.model.MealsListDTO
import com.shop.domain.feature.meallist.model.Meal

class MealsListDTOToDomainMapper {
    fun mealsListDTOToDomainMeals(mealsListDTO: MealsListDTO): List<Meal> {
        return mealsListDTO.meals.map {
            Meal(
                it.mealId, it.meal, it.mealThumb
            )
        }
    }
}