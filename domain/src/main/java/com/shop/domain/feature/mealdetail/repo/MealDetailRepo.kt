package com.shop.domain.feature.mealdetail.repo

import com.shop.domain.feature.mealdetail.model.MealDetail

interface MealDetailRepo {
    suspend fun getMealById(mealId: String): MealDetail
}