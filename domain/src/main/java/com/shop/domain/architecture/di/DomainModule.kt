package com.shop.domain.architecture.di

import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCase
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCaseImpl
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import com.shop.domain.feature.meallist.usecase.GetMealsUseCaseImpl
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCase
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: DefaultDispatchersProvider): DispatchersProvider

    @Binds
    abstract fun bindGetMealCategoriesUseCase(useCase: GetMealCategoriesUseCaseImpl): GetMealCategoriesUseCase

    @Binds
    abstract fun bindGetMealsByCategoryUseCase(useCase: GetMealsUseCaseImpl): GetMealsUseCase

    @Binds
    abstract fun bindGetMealDetailsUseCase(useCase: GetMealDetailUseCaseImpl): GetMealDetailUseCase

}
