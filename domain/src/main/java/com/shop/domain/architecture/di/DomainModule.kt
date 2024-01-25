package com.shop.domain.architecture.di

import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.architecture.coroutinecontext.DispatchersProvider
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCase
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCase
import com.shop.domain.feature.meallist.repo.MealsRepo
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: DefaultDispatchersProvider): DispatchersProvider

    companion object {
        @Provides
        fun provideGetMealCategoriesUseCase(
            mealsCategoryRepo: MealsCategoryRepo,
            dispatchersProvider: DefaultDispatchersProvider
        ) = GetMealCategoriesUseCase(mealsCategoryRepo, dispatchersProvider)

        @Provides
        fun provideGetMealsByCategoryUseCase(
            mealsRepo: MealsRepo,
            dispatchersProvider: DefaultDispatchersProvider
        ) = GetMealsUseCase(mealsRepo, dispatchersProvider)

        @Provides
        fun provideGetMealDetailsUseCase(
            mealDetailRepo: MealDetailRepo,
            dispatchersProvider: DefaultDispatchersProvider
        ) = GetMealDetailUseCase(mealDetailRepo, dispatchersProvider)
    }
}
