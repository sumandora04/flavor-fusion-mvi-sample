package com.shop.presentation.view.mealdetail

import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.mealdetail.model.PresentationMealDetail

sealed interface MealsDetailState : ViewState {
    data object Loading : MealsDetailState
    data class MealDetailSuccess(val mealDetail: PresentationMealDetail) : MealsDetailState
    data class Error(val error: String?) : MealsDetailState
}