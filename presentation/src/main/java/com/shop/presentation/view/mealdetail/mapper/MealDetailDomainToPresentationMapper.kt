package com.shop.presentation.view.mealdetail.mapper

import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.presentation.view.mealdetail.model.PresentationMealDetail


class MealDetailDomainToPresentationMapper {
    fun mealDetailToPresentationMealDetail(mealDetail: MealDetail): PresentationMealDetail {
        return with(mealDetail) {
            PresentationMealDetail(
                mealId = mealId,
                mealArea = mealArea,
                mealCategory = mealCategory,
                mealName = mealName,
                mealImage = mealImage,
                instructions = instructions
            )
        }
    }
}