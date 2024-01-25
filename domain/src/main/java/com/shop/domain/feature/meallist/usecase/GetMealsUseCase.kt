package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val mealsRepo: MealsRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<String, List<Meal>> {
    override operator fun invoke(parameter: String) = flow<Events<List<Meal>>> {
        try {
            val meals = mealsRepo.getMealsByCategory(parameter)
            emit(Events.Success(meals))
        } catch (e: Exception) {
            emit(Events.Error(e.message.toString()))
        }
    }.flowOn(dispatchersProvider.io())
}