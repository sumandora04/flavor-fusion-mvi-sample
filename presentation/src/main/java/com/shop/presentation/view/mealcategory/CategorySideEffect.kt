package com.shop.presentation.view.mealcategory

import com.shop.presentation.architecture.viewmodel.SideEffect
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory

sealed interface CategorySideEffect : SideEffect {
    data class NavigateToMealsList(val category:PresentationMealCategory): CategorySideEffect
}