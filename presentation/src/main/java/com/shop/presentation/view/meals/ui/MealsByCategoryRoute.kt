package com.shop.presentation.view.meals.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.shop.presentation.R
import com.shop.presentation.architecture.ui.widgets.AppToolBar
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory
import com.shop.presentation.view.meals.MealsByCategoryIntent
import com.shop.presentation.view.meals.MealsByCategorySideEffect
import com.shop.presentation.view.meals.MealsByCategoryViewModel


@Composable
fun MealsByCategoryRoute(
    modifier: Modifier = Modifier,
    category: PresentationMealCategory?,
    onGoBack: () -> Unit,
    onGoToSelectedItem: (String) -> Unit,
    viewModel: MealsByCategoryViewModel = hiltViewModel()
) {
    if (category == null) {
        return
    }
    val state by viewModel.state.collectAsState()
    var shouldCallApi by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldCallApi) {
        LaunchedEffect(key1 = category) {
            viewModel.setIntent(
                MealsByCategoryIntent.FetchMealsByCategory(
                    categoryName = category.category
                )
            )
            shouldCallApi = false
        }
    }
    LaunchedEffect(key1 = category) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is MealsByCategorySideEffect.NavigateToMealDetail -> {
                    onGoToSelectedItem(sideEffect.mealId)
                }
            }

        }
    }

    AppToolBar(
        title = stringResource(id = R.string.meal_category_name, category.category),
        image = category.categoryImage,
        content = { innerPadding ->
            MealsByCategoryScreen(
                state = state, viewModel = viewModel, modifier = modifier.padding(innerPadding)
            )
        },
        onBackIconPressed = onGoBack,
    )
}