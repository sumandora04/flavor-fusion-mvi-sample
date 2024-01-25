package com.shop.presentation.view.meals.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shop.presentation.architecture.ui.state.AppErrorState
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.meals.MealsByCategoryState
import com.shop.presentation.view.meals.MealsByCategoryViewModel

@Composable
fun MealsByCategoryScreen(
    viewModel: MealsByCategoryViewModel,
    state: ViewState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is MealsByCategoryState.Loading -> CircularProgress()
        is MealsByCategoryState.MealsList -> MealList(
            meals = state.meals, viewModel = viewModel, modifier = modifier
        )

        is MealsByCategoryState.Error -> AppErrorState(message = state.error)
    }
}
