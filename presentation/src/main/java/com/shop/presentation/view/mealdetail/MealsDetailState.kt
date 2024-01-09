package com.shop.presentation.view.mealdetail

import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.presentation.architecture.viewmodel.ViewState

sealed class MealsDetailState : ViewState {
    data object Loading: MealsDetailState()
    data class MealDetailSuccess(val mealDetail: MealDetail): MealsDetailState()
    data class Error(val error: String?): MealsDetailState()
}