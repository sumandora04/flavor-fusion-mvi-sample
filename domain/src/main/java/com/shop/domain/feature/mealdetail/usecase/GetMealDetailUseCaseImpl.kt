package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealDetailUseCaseImpl @Inject constructor(
    private val mealsRepo: MealDetailRepo,
    private val dispatchersProvider: DispatchersProvider
) : GetMealDetailUseCase {
    override operator fun invoke(parameter: String) = flow<Events<MealDetail>> {
        emit(Events.Loading())
        emit(Events.Success(mealsRepo.getMealById(parameter)))
    }.catch {
        emit(Events.Error(it.message.toString()))
    }.flowOn(dispatchersProvider.io())
}