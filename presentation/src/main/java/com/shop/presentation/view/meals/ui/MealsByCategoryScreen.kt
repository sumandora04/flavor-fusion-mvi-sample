package com.shop.presentation.view.meals.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.meallist.model.Meal
import com.shop.presentation.R
import com.shop.presentation.architecture.ui.widgets.AppErrorState
import com.shop.presentation.architecture.ui.widgets.AppToolBar
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.ui.widgets.ImageLoader
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.meals.MealsByCategoryIntent
import com.shop.presentation.view.meals.MealsByCategoryState
import com.shop.presentation.view.meals.MealsByCategoryViewModel


@Composable
fun MealsByCategoryRoute(
    modifier: Modifier = Modifier,
    category: MealCategory?,
    onGoBack: () -> Unit,
    onGoToSelectedItem: (String) -> Unit,
    viewModel: MealsByCategoryViewModel = hiltViewModel()
) {
    if (category == null) {
        return
    }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = category) {
        viewModel.setIntent(
            MealsByCategoryIntent.FetchMealsByCategory(
                categoryName = category.category
            )
        )
    }

    AppToolBar(
        title = stringResource(id = R.string.meal_category_name, category.category),
        image = category.categoryImage,
        content = { innerPadding ->
            MealsByCategoryScreen(
                state = state,
                onGoToSelectedItem = onGoToSelectedItem,
                modifier = modifier.padding(innerPadding)
            )
        },
        onBackIconPressed = onGoBack,
    )
}

@Composable
fun MealsByCategoryScreen(
    onGoToSelectedItem: (String) -> Unit, state: ViewState, modifier: Modifier = Modifier
) {
    when (state) {
        is MealsByCategoryState.Loading   -> CircularProgress()
        is MealsByCategoryState.MealsList -> Meals(
            meals = state.meals, onGoToSelectedItem = onGoToSelectedItem, modifier = modifier
        )

        is MealsByCategoryState.Error     -> AppErrorState(message = state.error)
    }
}


@Composable
fun Meals(
    meals: List<Meal>, modifier: Modifier = Modifier, onGoToSelectedItem: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = meals) {
            Meal(meal = it, onGoToSelectedItem)
        }
    }
}

@Composable
fun Meal(meal: Meal, onGoToSelectedItem: (String) -> Unit) {
    Card(shape = MaterialTheme.shapes.small, modifier = Modifier.padding(vertical = 10.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(180.dp)
                   .clickable {
                       onGoToSelectedItem(meal.mealId)
                   }) {
            ImageLoader(
                imageData = meal.mealImage, Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = meal.mealName,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}