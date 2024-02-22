package com.shop.presentation.view.mealdetail.ui

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
import com.shop.presentation.view.mealdetail.MealDetailIntent
import com.shop.presentation.view.mealdetail.MealDetailViewModel

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
    var shouldCallApi by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = savableMealId, shouldCallApi) {
        if (shouldCallApi) {
            viewModel.setIntent(MealDetailIntent.FetchMealDetailById(savableMealId))
            shouldCallApi = false
        }
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