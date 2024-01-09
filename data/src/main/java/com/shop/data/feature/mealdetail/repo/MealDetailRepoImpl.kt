package com.shop.data.feature.mealdetail.repo

import com.shop.data.feature.mealdetail.mapper.toDomainMealDetail
import com.shop.data.architecture.network.MealsApi
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import javax.inject.Inject

class MealDetailRepoImpl @Inject constructor(private val api: MealsApi) : MealDetailRepo {
    override suspend fun getMealById(mealId: String): MealDetail {
        return api.getMealDetailById(mealId).toDomainMealDetail()
    }
}