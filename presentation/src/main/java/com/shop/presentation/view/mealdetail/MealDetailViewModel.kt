package com.shop.presentation.view.mealdetail

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.view.mealdetail.mapper.MealDetailDomainToPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val useCase: GetMealDetailUseCase,
    private val mealDetailMapper: MealDetailDomainToPresentationMapper
) : BaseViewModel<MealsDetailState, MealDetailIntent, MealDetailSideEffect>(
    MealsDetailState.Loading
) {
    override fun handleIntent(intent: MealDetailIntent) {
        when (intent) {
            is MealDetailIntent.FetchMealDetailById -> fetchMealDetailById(intent.mealId)
        }
    }

    private fun fetchMealDetailById(mealId: String) {
        viewModelScope.launch {
            emitState(MealsDetailState.Loading)
            when (val result: Events<MealDetail> = useCase(mealId)) {
                is Events.Success -> {
                    try {
                        result.data?.let { mealDetail ->
                            MealsDetailState.MealDetailSuccess(
                                mealDetailMapper.mealDetailToPresentationMealDetail(
                                    mealDetail
                                )
                            )
                        }
                    } catch (e: Exception) {
                        MealsDetailState.Error(e.localizedMessage)
                    }?.let { mealDetailState -> emitState(mealDetailState) }
                }

                is Events.Error   -> {
                    emitState(MealsDetailState.Error(result.message))
                }
            }
        }
    }
}