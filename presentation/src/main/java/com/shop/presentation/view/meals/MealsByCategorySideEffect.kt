package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.SideEffect

sealed interface MealsByCategorySideEffect : SideEffect {
    class NavigateToMealDetail(val mealId: String) : MealsByCategorySideEffect

}