package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(
    private val mealsRepo: MealDetailRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<String, MealDetail> {
    override operator fun invoke(parameter: String) = flow<Events<MealDetail>> {
        try {
            val meal = mealsRepo.getMealById(parameter)
            emit(Events.Success(meal))
        } catch (e: Exception) {
            emit(Events.Error(e.message.toString()))
        }
    }.flowOn(dispatchersProvider.io())
}