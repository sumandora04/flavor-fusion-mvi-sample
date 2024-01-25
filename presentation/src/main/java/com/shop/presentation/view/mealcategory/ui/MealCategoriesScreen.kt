package com.shop.presentation.view.mealcategory.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shop.presentation.architecture.ui.state.AppErrorState
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.mealcategory.MealCategoryViewModel
import com.shop.presentation.view.mealcategory.MealsCategoriesState

@Composable
fun MealCategoriesScreen(
    modifier: Modifier = Modifier,
    state: ViewState,
    viewModel: MealCategoryViewModel
) {
    when (state) {
        is MealsCategoriesState.Loading        -> CircularProgress()
        is MealsCategoriesState.MealCategories -> CategoryList(
            mealCategories = state.categories,
            viewModel = viewModel,
            modifier = modifier
        )

        is MealsCategoriesState.Error          -> AppErrorState(message = state.error)
    }
}