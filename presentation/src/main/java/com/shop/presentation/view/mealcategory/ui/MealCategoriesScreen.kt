package com.shop.presentation.view.mealcategory.ui

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
import com.shop.presentation.R
import com.shop.presentation.architecture.ui.widgets.AppErrorState
import com.shop.presentation.architecture.ui.widgets.AppIdleState
import com.shop.presentation.architecture.ui.widgets.AppToolBar
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.ui.widgets.ImageLoader
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.view.mealcategory.CategoriesIntent
import com.shop.presentation.view.mealcategory.MealCategoryViewModel
import com.shop.presentation.view.mealcategory.MealsCategoriesState


@Composable
fun MealCategoryRoute(
    onGoToSelectedItem: (MealCategory) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MealCategoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = "CategoryList") {
        viewModel.setIntent(CategoriesIntent.FetchMealsCategories)
    }

    AppToolBar(
        title = stringResource(id = R.string.app_name),
        image = R.mipmap.ic_launcher,
        content = { innerPadding ->
            MealCategoriesScreen(
                modifier = modifier.padding(innerPadding),
                state = state,
                onGoToSelectedItem = onGoToSelectedItem
            )
        },
    )
}

@Composable
fun MealCategoriesScreen(
    modifier: Modifier = Modifier,
    state: ViewState,
    onGoToSelectedItem: (MealCategory) -> Unit,
) {
    when (state) {
        is MealsCategoriesState.Loading        -> CircularProgress()
        is MealsCategoriesState.MealCategories -> CategoryList(
            mealCategories = state.categories,
            onGoToSelectedItem = onGoToSelectedItem,
            modifier = modifier
        )

        is MealsCategoriesState.Error          -> AppErrorState(message = state.error)
    }
}


@Composable
fun CategoryList(
    mealCategories: List<MealCategory>,
    onGoToSelectedItem: (MealCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = mealCategories) {
            Category(mealCategory = it, onGoToSelectedItem = onGoToSelectedItem)
        }
    }
}

@Composable
fun Category(mealCategory: MealCategory, onGoToSelectedItem: (MealCategory) -> Unit) {
    Card(shape = MaterialTheme.shapes.small, modifier = Modifier.padding(vertical = 10.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(150.dp)
                   .clickable {
                       onGoToSelectedItem(mealCategory)
                   }) {
            ImageLoader(
                imageData = mealCategory.categoryImage, Modifier.size(110.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mealCategory.category,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}