package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val mealsRepo: MealsCategoryRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<Unit, List<MealCategory>> {
    override operator fun invoke(parameter: Unit): Flow<Events<List<MealCategory>>> = flow {
        try {
            val meals = mealsRepo.getMealsCategory()
            emit(Events.Success(meals))
        } catch (e: Exception) {
            emit(Events.Error(e.message.toString()))
        }
    }.flowOn(dispatchersProvider.io())
}