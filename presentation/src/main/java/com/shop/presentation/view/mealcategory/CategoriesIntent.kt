package com.shop.presentation.view.mealcategory

import com.shop.presentation.architecture.viewmodel.ViewIntent
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory

sealed interface CategoriesIntent: ViewIntent {
    data object FetchMealsCategories : CategoriesIntent
    class OnCategoryItemClick(val category: PresentationMealCategory) : CategoriesIntent
}