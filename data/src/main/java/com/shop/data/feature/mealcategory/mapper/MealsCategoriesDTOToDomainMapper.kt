package com.shop.data.feature.mealcategory.mapper

import com.shop.data.feature.mealcategory.model.MealsCategoriesDTO
import com.shop.domain.feature.mealcategory.model.MealCategory

fun MealsCategoriesDTO.toDomainCategories(): List<MealCategory> {
    return this.categories.map {
        MealCategory(
            it.categoryId, it.category, it.categoryThumb
        )
    }
}