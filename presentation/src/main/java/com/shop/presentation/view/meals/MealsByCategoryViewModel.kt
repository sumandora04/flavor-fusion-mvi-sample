package com.shop.presentation.view.meals

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.meallist.model.Meal
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.view.meals.mapper.MealsDomainToPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsByCategoryViewModel @Inject constructor(
    private val useCase: UseCase<String, List<Meal>>,
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
            when (val result: Events<List<Meal>> = useCase(category)) {
                is Events.Success -> {
                    emitState(
                        MealsByCategoryState.MealsList(
                            mealsListMapper.mealListToPresentationMealList(result.data.orEmpty())
                        )
                    )
                }

                is Events.Error   -> {
                    emitState(MealsByCategoryState.Error(result.message))
                }
            }
        }
    }
}