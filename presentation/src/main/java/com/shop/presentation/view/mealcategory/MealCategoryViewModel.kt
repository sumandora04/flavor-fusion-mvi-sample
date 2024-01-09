package com.shop.presentation.view.mealcategory

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.architecture.viewmodel.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoryViewModel @Inject constructor(private val useCase: GetMealCategoriesUseCase) :
    BaseViewModel<MealsCategoriesState, CategoriesIntent>(MealsCategoriesState.Loading) {

    override fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is CategoriesIntent.FetchMealsCategories -> fetchMealsCategories()
        }
    }

    private fun fetchMealsCategories() {
        viewModelScope.launch {
            useCase.invoke(Unit).onEach { event ->
                when (event) {
                    is Events.Loading -> {
                        setState(MealsCategoriesState.Loading)
                    }

                    is Events.Success -> {
                        setState(
                            try {
                                MealsCategoriesState.MealCategories(event.data.orEmpty())
                            } catch (e: Exception) {
                                MealsCategoriesState.Error(e.localizedMessage)
                            }
                        )
                    }

                    is Events.Error   -> {
                        setState(MealsCategoriesState.Error(event.message))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}