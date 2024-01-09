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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.presentation.R
import com.shop.presentation.architecture.viewmodel.ViewState
import com.shop.presentation.architecture.ui.widgets.AppErrorState
import com.shop.presentation.architecture.ui.widgets.AppIdleState
import com.shop.presentation.architecture.ui.widgets.AppToolBar
import com.shop.presentation.architecture.ui.widgets.CircularProgress
import com.shop.presentation.architecture.ui.widgets.ImageLoader
import com.shop.presentation.view.mealdetail.MealDetailIntent
import com.shop.presentation.view.mealdetail.MealDetailViewModel
import com.shop.presentation.view.mealdetail.MealsDetailState

@Composable
fun MealDetailRoute(
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier,
    mealId: String,
    viewModel: MealDetailViewModel = hiltViewModel()
) {
    val savableMealId by rememberSaveable {
        mutableStateOf(mealId)
    }
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = savableMealId) {
        viewModel.setIntent(MealDetailIntent.FetchMealDetailById(savableMealId))
    }

    AppToolBar(
        title = stringResource(id = R.string.cooking_instruction),
        image = R.mipmap.ic_launcher,
        content = { innerPadding ->
            MealDetailScreen(
                modifier = modifier.padding(innerPadding),
                state = state,
            )
        },
        onBackIconPressed = onGoBack
    )

}

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

@Composable
fun MealDetails(mealDetail: MealDetail, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                PaddingValues(16.dp)
            )
            .verticalScroll(
                state = scrollState, enabled = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        ImageLoader(imageData = mealDetail.mealImage, Modifier.fillMaxWidth(1f))
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = mealDetail.mealName,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.cooking_instruction),
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = mealDetail.instructions, style = MaterialTheme.typography.bodyMedium)
    }
}