package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.SideEffect

sealed interface MealsByCategorySideEffect : SideEffect {
    data class NavigateToMealDetail(val mealId: String) : MealsByCategorySideEffect

}