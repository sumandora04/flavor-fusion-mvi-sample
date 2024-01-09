package com.shop.domain.feature.mealcategory.repo

import com.shop.domain.feature.mealcategory.model.MealCategory

interface MealsCategoryRepo {
    suspend fun getMealsCategory(): List<MealCategory>
}