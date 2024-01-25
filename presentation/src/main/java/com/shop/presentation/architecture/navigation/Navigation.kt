package com.shop.presentation.architecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory
import com.shop.presentation.view.mealcategory.ui.MealCategoryRoute
import com.shop.presentation.view.mealdetail.ui.MealDetailRoute
import com.shop.presentation.view.meals.ui.MealsByCategoryRoute

const val NAV_KEY_MEAL_CATEGORY = "meal_category"
const val NAV_KEY_MEAL_ID = "meal_id"

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MealsCategories.route) {
        composable(route = Screen.MealsCategories.route) {
            MealCategoryRoute(onGoToSelectedItem = { category ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = NAV_KEY_MEAL_CATEGORY, value = category
                )
                navController.navigate(Screen.MealsListByCategory.route)
            })
        }

        composable(route = Screen.MealsListByCategory.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<PresentationMealCategory>(
                    NAV_KEY_MEAL_CATEGORY
                )
            MealsByCategoryRoute(
                category = category,
                onGoBack = { navController.popBackStack() },
                onGoToSelectedItem = { mealId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = NAV_KEY_MEAL_ID, value = mealId
                    )
                    navController.navigate(Screen.MealDetails.route)
                },
            )
        }

        composable(
            route = Screen.MealDetails.route
        ) {
            val mealId: String =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>(NAV_KEY_MEAL_ID)
                    ?: ""

            MealDetailRoute(mealId = mealId, onGoBack = {
                navController.popBackStack()
            })
        }

    }
}