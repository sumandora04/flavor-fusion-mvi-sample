package com.shop.data.feature.mealcategory.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.mealcategory.mapper.MealsCategoriesDTOToDomainMapper
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import javax.inject.Inject

class MealsCategoryRepoImpl @Inject constructor(
    private val api: MealsApi,
    private val mealsCategoriesDTOToDomainMapper: MealsCategoriesDTOToDomainMapper
) : MealsCategoryRepo {
    override suspend fun getMealsCategory(): List<MealCategory> {
        return mealsCategoriesDTOToDomainMapper.mealsCategoriesDTOtoDomainCategories(api.getMealsCategory())
    }
}