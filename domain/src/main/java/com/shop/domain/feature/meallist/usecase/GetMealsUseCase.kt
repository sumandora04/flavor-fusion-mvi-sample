package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.architecture.usecases.UseCase
import kotlinx.coroutines.flow.Flow


interface GetMealsUseCase: UseCase<String, List<Meal>> {
    override fun invoke(parameter: String): Flow<Events<List<Meal>>>
}