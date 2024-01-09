package com.shop.presentation.view.meals

import com.shop.presentation.architecture.viewmodel.ViewIntent

sealed class MealsByCategoryIntent : ViewIntent {
    data class FetchMealsByCategory(val categoryName: String) : MealsByCategoryIntent()
}