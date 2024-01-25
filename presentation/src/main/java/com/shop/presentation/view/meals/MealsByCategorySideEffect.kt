package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.SideEffect

interface MealsByCategorySideEffect : SideEffect {
    class NavigateToMealDetail(val mealId: String) : MealsByCategorySideEffect

}