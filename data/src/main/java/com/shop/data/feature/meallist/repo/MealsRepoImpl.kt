package com.shop.data.feature.meallist.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.meallist.mapper.MealsListDTOToDomainMapper
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import javax.inject.Inject

class MealsRepoImpl @Inject constructor(
    private val api: MealsApi, private val mealsListDTOToDomainMapper: MealsListDTOToDomainMapper
) : MealsRepo {
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        return mealsListDTOToDomainMapper.mealsListDTOToDomainMeals(api.getMealsByCategory(category))
    }
}