package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.architecture.usecases.UseCase
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val mealsRepo: MealsCategoryRepo, private val dispatchersProvider: DispatchersProvider
) : UseCase<Unit, List<MealCategory>> {
    override suspend operator fun invoke(parameter: Unit): Events<List<MealCategory>> =
        withContext(dispatchersProvider.io) {
            try {
                val meals = mealsRepo.getMealsCategory()
                Events.Success(meals)
            } catch (e: Exception) {
                Events.Error(e.message.toString())
            }
        }
}