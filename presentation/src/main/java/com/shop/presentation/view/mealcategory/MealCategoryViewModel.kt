package com.shop.presentation.view.mealcategory

import androidx.lifecycle.viewModelScope
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCase
import com.shop.presentation.architecture.viewmodel.BaseViewModel
import com.shop.presentation.view.mealcategory.mapper.MealCategoryDomainToPresentationMapper
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoryViewModel @Inject constructor(
    private val useCase: GetMealCategoriesUseCase,
    private val mealCategoryMapper: MealCategoryDomainToPresentationMapper
) : BaseViewModel<MealsCategoriesState, CategoriesIntent, CategorySideEffect>(
    MealsCategoriesState.Loading
) {

    override fun handleIntent(intent: CategoriesIntent) {
        when (intent) {
            is CategoriesIntent.FetchMealsCategories -> fetchMealsCategories()
            is CategoriesIntent.OnCategoryItemClick  -> navigateToMealsList(intent.category)
        }
    }

    fun navigateToMealsList(category: PresentationMealCategory) {
        viewModelScope.launch {
            emitEffect(CategorySideEffect.NavigateToMealsList(category))
        }
    }

    private fun fetchMealsCategories() {
        viewModelScope.launch {
            emitState(MealsCategoriesState.Loading)
            when (val result: Events<List<MealCategory>> = useCase(Unit)) {
                is Events.Success -> {
                    emitState(
                        try {
                            MealsCategoriesState.MealCategories(result.data.orEmpty().map {
                                mealCategoryMapper.mealCategoryToPresenterMealCategory(it)
                            })
                        } catch (e: Exception) {
                            MealsCategoriesState.Error(e.localizedMessage)
                        }
                    )
                }

                is Events.Error   -> {
                    emitState(MealsCategoriesState.Error(result.message))
                }
            }
        }
    }
}