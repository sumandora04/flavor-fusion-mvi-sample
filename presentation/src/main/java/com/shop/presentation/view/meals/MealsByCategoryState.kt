package com.shop.presentation.view.meals

import com.shop.domain.feature.meallist.model.Meal
import com.shop.presentation.architecture.viewmodel.ViewState

sealed class MealsByCategoryState : ViewState {
    data object Loading: MealsByCategoryState()
    data class MealsList(val meals: List<Meal>): MealsByCategoryState()
    data class Error(val error: String?): MealsByCategoryState()
}