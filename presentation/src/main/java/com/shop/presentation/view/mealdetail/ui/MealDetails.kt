package com.shop.presentation.view.mealdetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.shop.presentation.R
import com.shop.presentation.architecture.ui.theme.DEFAULT_CONTENT_PADDING_SIZE
import com.shop.presentation.architecture.ui.theme.DEFAULT_PADDING_SIZE
import com.shop.presentation.architecture.ui.widgets.ImageLoader
import com.shop.presentation.view.mealdetail.model.PresentationMealDetail

@Composable
fun MealDetails(mealDetail: PresentationMealDetail, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                PaddingValues(DEFAULT_CONTENT_PADDING_SIZE)
            )
            .verticalScroll(
                state = scrollState, enabled = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        ImageLoader(imageData = mealDetail.mealImage, Modifier.fillMaxWidth(1f))
        Spacer(modifier = Modifier.height(DEFAULT_PADDING_SIZE))
        Text(
            text = mealDetail.mealName,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(DEFAULT_PADDING_SIZE))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Origin: ${mealDetail.mealArea}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Text(
                text = "Category: ${mealDetail.mealCategory}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(DEFAULT_PADDING_SIZE))
        Text(
            text = stringResource(id = R.string.cooking_instruction),
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(DEFAULT_PADDING_SIZE))
        Text(text = mealDetail.instructions, style = MaterialTheme.typography.bodyMedium)
    }
}