package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.architecture.usecases.UseCase
import kotlinx.coroutines.flow.Flow


interface GetMealDetailUseCase : UseCase<String, MealDetail> {
    override operator fun invoke(parameter: String): Flow<Events<MealDetail>>
}