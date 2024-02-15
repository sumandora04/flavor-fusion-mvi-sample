package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.meals.model.PresentationMeal

sealed interface MealsByCategoryState : ViewState {
    data object Loading : MealsByCategoryState
    data class MealsList(val meals: List<PresentationMeal>) : MealsByCategoryState
    data class Error(val error: String?) : MealsByCategoryState
}