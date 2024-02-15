package com.shop.presentation.view.mealcategory.mapper

import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory

class MealCategoryDomainToPresentationMapper {
    fun mealCategoryToPresenterMealCategory(mealCategory: MealCategory): PresentationMealCategory {
        return with(mealCategory) {
            PresentationMealCategory(
                categoryId = categoryId,
                category = category,
                categoryImage = categoryImage
            )
        }
    }
}