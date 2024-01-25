package com.shop.presentation.view.meals.mapper

import com.shop.domain.feature.meallist.model.Meal
import com.shop.presentation.view.meals.model.PresentationMeal

class MealsDomainToPresentationMapper {
    fun mealListToPresentationMealList(meals: List<Meal>): List<PresentationMeal> {
        return meals.map {
            PresentationMeal(
                mealId = it.mealId, mealName = it.mealName, mealImage = it.mealImage
            )
        }
    }
}