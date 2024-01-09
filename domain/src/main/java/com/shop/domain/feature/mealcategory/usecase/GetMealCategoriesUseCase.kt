package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.architecture.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetMealCategoriesUseCase : UseCase<Unit, List<MealCategory>> {
    override operator fun invoke(parameter: Unit): Flow<Events<List<MealCategory>>>
}

