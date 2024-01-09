package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealsUseCaseImpl @Inject constructor(
    private val mealsRepo: MealsRepo,
    private val dispatchersProvider: DispatchersProvider
) : GetMealsUseCase {
    override operator fun invoke(parameter: String) = flow<Events<List<Meal>>> {
        emit(Events.Loading())
        emit(Events.Success(mealsRepo.getMealsByCategory(parameter)))
    }.catch {
        emit(Events.Error(it.message.toString()))
    }.flowOn(dispatchersProvider.io())
}