package com.shop.presentation.view.meals.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.shop.presentation.architecture.ui.theme.DEFAULT_PADDING_SIZE
import com.shop.presentation.architecture.ui.theme.SIZE_100_DP
import com.shop.presentation.architecture.ui.theme.SIZE_180_DP
import com.shop.presentation.architecture.ui.widgets.ImageLoader
import com.shop.presentation.view.meals.MealsByCategoryIntent
import com.shop.presentation.view.meals.MealsByCategoryViewModel
import com.shop.presentation.view.meals.model.PresentationMeal

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    meal: PresentationMeal,
    viewModel: MealsByCategoryViewModel
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(vertical = DEFAULT_PADDING_SIZE)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(SIZE_180_DP)
                   .clickable { viewModel.setIntent(MealsByCategoryIntent.OnMealItemClick(meal.mealId)) }) {
            ImageLoader(
                imageData = meal.mealImage, Modifier.size(SIZE_100_DP)
            )
            Spacer(modifier = Modifier.height(DEFAULT_PADDING_SIZE))
            Text(
                text = meal.mealName,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}