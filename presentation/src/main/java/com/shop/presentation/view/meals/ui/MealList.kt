package com.shop.presentation.view.meals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shop.presentation.architecture.ui.theme.DEFAULT_CONTENT_PADDING_SIZE
import com.shop.presentation.architecture.ui.theme.DEFAULT_PADDING_SIZE
import com.shop.presentation.architecture.ui.theme.SIZE_100_DP
import com.shop.presentation.view.meals.MealsByCategoryViewModel
import com.shop.presentation.view.meals.model.PresentationMeal

@Composable
fun MealList(
    meals: List<PresentationMeal>,
    modifier: Modifier = Modifier,
    viewModel: MealsByCategoryViewModel
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_PADDING_SIZE),
        columns = GridCells.Adaptive(SIZE_100_DP),
        contentPadding = PaddingValues(DEFAULT_CONTENT_PADDING_SIZE),
    ) {
        items(items = meals) { meal ->
            MealItem(meal = meal, viewModel = viewModel)
        }
    }
}