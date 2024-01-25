package com.shop.data.feature.mealdetail.mapper

import com.shop.data.feature.mealdetail.model.MealDetailListDTO
import com.shop.domain.feature.mealdetail.model.MealDetail


class MealDetailListDTOToDomainMapper {
    fun mealDetailListDTOToDomainMealDetail(mealDetailListDTO: MealDetailListDTO): MealDetail {
        val mealDetail = mealDetailListDTO.meals.first()
        return mealDetail.let {
            MealDetail(
                it.mealId, it.area, it.category, it.instructions, it.meal, it.mealThumb
            )
        }
    }
}