package com.shop.domain.feature.meallist.repo

import com.shop.domain.feature.meallist.model.Meal

interface MealsRepo {
    suspend fun getMealsByCategory(category: String): List<Meal>
}