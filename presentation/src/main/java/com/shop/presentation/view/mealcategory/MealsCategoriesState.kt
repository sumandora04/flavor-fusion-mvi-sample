package com.shop.presentation.view.mealcategory

import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory

sealed class MealsCategoriesState : ViewState {
    data object Loading: MealsCategoriesState()
    data class MealCategories(val categories: List<PresentationMealCategory>): MealsCategoriesState()
    data class Error(val error: String?): MealsCategoriesState()
}