package com.shop.presentation.view.mealdetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shop.presentation.architecture.ui.state.AppErrorState
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.mealdetail.MealsDetailState


@Composable
fun MealDetailScreen(modifier: Modifier = Modifier, state: ViewState) {
    when (state) {
        is MealsDetailState.Loading           -> CircularProgress()
        is MealsDetailState.MealDetailSuccess -> MealDetails(
            mealDetail = state.mealDetail, modifier = modifier
        )

        is MealsDetailState.Error             -> AppErrorState(message = state.error)
    }
}
