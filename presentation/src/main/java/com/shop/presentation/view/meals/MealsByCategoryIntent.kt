package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.ViewIntent

sealed interface MealsByCategoryIntent : ViewIntent {
    data class FetchMealsByCategory(val categoryName: String) : MealsByCategoryIntent
    class OnMealItemClick(val mealId: String) : MealsByCategoryIntent

}