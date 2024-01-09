package com.shop.presentation.view.mealdetail

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.architecture.viewmodel.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(private val useCase: GetMealDetailUseCase) :
    BaseViewModel<MealsDetailState, MealDetailIntent>(MealsDetailState.Loading) {

    override fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is MealDetailIntent.FetchMealDetailById -> fetchMealDetailById(intent.mealId)
        }
    }

    private fun fetchMealDetailById(mealId: String) {
        viewModelScope.launch {
            useCase.invoke(mealId).onEach {
                when (it) {
                    is Events.Loading -> {
                        setState(MealsDetailState.Loading)
                    }

                    is Events.Success -> {
                        setState(
                            try {
                                MealsDetailState.MealDetailSuccess(
                                    it.data!!
                                )
                            } catch (e: Exception) {
                                MealsDetailState.Error(e.localizedMessage)
                            }
                        )
                    }

                    is Events.Error   -> {
                        setState(MealsDetailState.Error(it.message))
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}