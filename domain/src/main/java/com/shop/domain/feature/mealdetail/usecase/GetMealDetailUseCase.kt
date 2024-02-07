package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(
    private val mealsRepo: MealDetailRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<String, MealDetail> {
    override suspend operator fun invoke(parameter: String) =
        withContext(dispatchersProvider.io()) {
            try {
                val meal = mealsRepo.getMealById(parameter)
                Events.Success(meal)
            } catch (e: Exception) {
                Events.Error(e.message.toString())
            }
        }
}