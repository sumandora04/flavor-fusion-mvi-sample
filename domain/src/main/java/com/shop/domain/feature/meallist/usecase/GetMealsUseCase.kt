package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val mealsRepo: MealsRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<String, List<Meal>> {
    override suspend operator fun invoke(parameter: String) =
        withContext(dispatchersProvider.io) {
            try {
                val meals = mealsRepo.getMealsByCategory(parameter)
                Events.Success(meals)
            } catch (e: Exception) {
                Events.Error(e.message.toString())
            }
        }
}