package com.shop.presentation.architecture.di

import com.shop.presentation.view.mealcategory.mapper.MealCategoryDomainToPresentationMapper
import com.shop.presentation.view.mealdetail.mapper.MealDetailDomainToPresentationMapper
import com.shop.presentation.view.meals.mapper.MealsDomainToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PresentationModule {

    @Provides
    fun mealCategoryDomainToPresentationMapper() = MealCategoryDomainToPresentationMapper()

    @Provides
    fun mealDetailDomainToPresentationMapper() = MealDetailDomainToPresentationMapper()

    @Provides
    fun mealsDomainToPresentationMapper() = MealsDomainToPresentationMapper()
}