package com.shop.presentation.architecture.navigation

sealed class Screen(val route:String) {
    data object MealsCategories: Screen("meals_categories")
    data object MealsListByCategory: Screen("meals_list_category")
    data object MealDetails: Screen("meal_details")
}