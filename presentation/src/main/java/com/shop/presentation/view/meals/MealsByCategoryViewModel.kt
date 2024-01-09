package com.shop.presentation.view.meals

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.architecture.viewmodel.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsByCategoryViewModel @Inject constructor(
    private val useCase: GetMealsUseCase
) : BaseViewModel<MealsByCategoryState, MealsByCategoryIntent>(MealsByCategoryState.Loading) {

    override fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is MealsByCategoryIntent.FetchMealsByCategory -> fetchMealsByCategory(intent.categoryName)
        }
    }

    private fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            useCase.invoke(category).onEach {
                when (it) {
                    is Events.Loading -> {
                        setState(MealsByCategoryState.Loading)
                    }

                    is Events.Success -> {
                        setState(
                            try {
                                MealsByCategoryState.MealsList(
                                    it.data.orEmpty()
                                )
                            } catch (e: Exception) {
                                MealsByCategoryState.Error(e.localizedMessage)
                            }
                        )
                    }

                    is Events.Error   -> {
                        setState(MealsByCategoryState.Error(it.message))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}