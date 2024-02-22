package com.shop.presentation.view.mealcategory.ui

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
import com.shop.presentation.view.mealcategory.CategoriesIntent
import com.shop.presentation.view.mealcategory.CategorySideEffect
import com.shop.presentation.view.mealcategory.MealCategoryViewModel
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory


const val LAUNCHED_EFFECT_KEY = "CATEGORY_LIST"

@Composable
fun MealCategoryRoute(
    onGoToSelectedItem: (PresentationMealCategory) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MealCategoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var shouldCallApi by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = LAUNCHED_EFFECT_KEY, shouldCallApi) {
        if (shouldCallApi) {
            viewModel.setIntent(CategoriesIntent.FetchMealsCategories)
            shouldCallApi = false
        }

        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is CategorySideEffect.NavigateToMealsList -> {
                    onGoToSelectedItem(sideEffect.category)
                }
            }
        }

    }

    AppToolBar(
        title = stringResource(id = R.string.app_name),
        image = R.mipmap.ic_launcher,
        content = { innerPadding ->
            MealCategoriesScreen(
                modifier = modifier.padding(innerPadding), state = state, viewModel = viewModel
            )
        },
    )
}