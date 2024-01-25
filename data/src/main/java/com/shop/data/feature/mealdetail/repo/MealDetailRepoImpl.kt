package com.shop.data.feature.mealdetail.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.mealdetail.mapper.MealDetailListDTOToDomainMapper
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import javax.inject.Inject

class MealDetailRepoImpl @Inject constructor(
    private val api: MealsApi,
    private val mealDetailListDTOToDomainMapper: MealDetailListDTOToDomainMapper
) : MealDetailRepo {
    override suspend fun getMealById(mealId: String): MealDetail {
        return mealDetailListDTOToDomainMapper.mealDetailListDTOToDomainMealDetail(
            api.getMealDetailById(
                mealId
            )
        )
    }
}