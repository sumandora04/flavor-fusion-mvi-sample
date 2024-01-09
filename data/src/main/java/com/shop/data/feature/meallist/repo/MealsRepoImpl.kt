package com.shop.data.feature.meallist.repo

import com.shop.data.feature.meallist.mapper.toDomainMeals
import com.shop.data.architecture.network.MealsApi
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import javax.inject.Inject

class MealsRepoImpl @Inject constructor(private val api: MealsApi): MealsRepo {
    override suspend fun getMealsByCategory(category: String): List<Meal> {
      return api.getMealsByCategory(category).toDomainMeals()
    }
}