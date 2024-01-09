package com.shop.presentation.view.mealdetail

import com.shop.presentation.architecture.viewmodel.ViewIntent

sealed class MealDetailIntent : ViewIntent {
    data class FetchMealDetailById(val mealId: String) : MealDetailIntent()
}