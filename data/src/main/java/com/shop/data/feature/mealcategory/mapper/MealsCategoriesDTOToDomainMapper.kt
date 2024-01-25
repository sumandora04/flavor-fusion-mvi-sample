package com.shop.data.feature.mealcategory.mapper

import com.shop.data.feature.mealcategory.model.MealsCategoriesDTO
import com.shop.domain.feature.mealcategory.model.MealCategory


class MealsCategoriesDTOToDomainMapper{
    fun mealsCategoriesDTOtoDomainCategories(mealsCategoriesDTO: MealsCategoriesDTO):
            List<MealCategory> {
        return mealsCategoriesDTO.categories.map {
            MealCategory(
                it.categoryId, it.category, it.categoryThumb
            )
        }
    }
}
