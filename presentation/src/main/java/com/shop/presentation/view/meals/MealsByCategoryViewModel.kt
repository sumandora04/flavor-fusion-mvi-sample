package com.shop.presentation.view.meals

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.view.meals.mapper.MealsDomainToPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsByCategoryViewModel @Inject constructor(
    private val useCase: GetMealsUseCase,
    private val mealsListMapper: MealsDomainToPresentationMapper
) : BaseViewModel<MealsByCategoryState, MealsByCategoryIntent, MealsByCategorySideEffect>(
    MealsByCategoryState.Loading
) {

    override fun handleIntent(intent: MealsByCategoryIntent) {
        when (intent) {
            is MealsByCategoryIntent.FetchMealsByCategory -> fetchMealsByCategory(intent.categoryName)
            is MealsByCategoryIntent.OnMealItemClick      -> navigateToMealDetail(intent.mealId)
        }
    }

    fun navigateToMealDetail(mealId: String) {
        viewModelScope.launch {
            emitEffect(MealsByCategorySideEffect.NavigateToMealDetail(mealId))
        }
    }

    private fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            emitState(MealsByCategoryState.Loading)
            useCase(category).collect {
                when (it) {
                    is Events.Success -> {
                        emitState(
                            try {
                                MealsByCategoryState.MealsList(
                                    mealsListMapper.mealListToPresentationMealList(it.data.orEmpty())
                                )
                            } catch (e: Exception) {
                                MealsByCategoryState.Error(e.localizedMessage)
                            }
                        )
                    }

                    is Events.Error   -> {
                        emitState(MealsByCategoryState.Error(it.message))
                    }
                }
            }
        }
    }
}