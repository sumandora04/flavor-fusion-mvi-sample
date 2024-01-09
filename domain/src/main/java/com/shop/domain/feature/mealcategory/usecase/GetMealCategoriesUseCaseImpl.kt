package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealCategoriesUseCaseImpl @Inject constructor(
    private val mealsRepo: MealsCategoryRepo,
    private val dispatchersProvider: DispatchersProvider
) : GetMealCategoriesUseCase {
    override operator fun invoke(parameter: Unit): Flow<Events<List<MealCategory>>> = flow {
        emit(Events.Loading())
        emit(Events.Success(mealsRepo.getMealsCategory()))
    }.catch {
        emit(Events.Error(it.message.toString()))
    }.flowOn(dispatchersProvider.io())
}