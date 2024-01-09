package com.shop.presentation.view.mealcategory

import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.presentation.architecture.viewmodel.ViewState

sealed class MealsCategoriesState : ViewState {
    data object Loading: MealsCategoriesState()
    data class MealCategories(val categories: List<MealCategory>): MealsCategoriesState()
    data class Error(val error: String?): MealsCategoriesState()
}