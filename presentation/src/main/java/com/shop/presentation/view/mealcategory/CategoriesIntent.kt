package com.shop.presentation.view.mealcategory

import com.shop.presentation.architecture.viewmodel.ViewIntent

sealed class CategoriesIntent: ViewIntent {
    data object FetchMealsCategories : CategoriesIntent()
}