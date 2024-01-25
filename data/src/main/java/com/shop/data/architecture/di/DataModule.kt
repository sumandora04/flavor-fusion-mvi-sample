package com.shop.data.architecture.di

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.mealcategory.mapper.MealsCategoriesDTOToDomainMapper
import com.shop.data.feature.mealcategory.repo.MealsCategoryRepoImpl
import com.shop.data.feature.mealdetail.mapper.MealDetailListDTOToDomainMapper
import com.shop.data.feature.mealdetail.repo.MealDetailRepoImpl
import com.shop.data.feature.meallist.mapper.MealsListDTOToDomainMapper
import com.shop.data.feature.meallist.repo.MealsRepoImpl
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import com.shop.domain.feature.meallist.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideMealsApiService(retrofit: Retrofit): MealsApi = retrofit.create(MealsApi::class.java)

    @Provides
    fun providesMealsListDTOToDomainMapper() = MealsListDTOToDomainMapper()

    @Provides
    fun mealRepo(api: MealsApi, mealsListDTOToDomainMapper: MealsListDTOToDomainMapper): MealsRepo =
        MealsRepoImpl(api, mealsListDTOToDomainMapper)

    @Provides
    fun providesMealsCategoriesDTOToDomainMapper() = MealsCategoriesDTOToDomainMapper()

    @Provides
    fun mealCategoryRepo(
        api: MealsApi, mealsCategoriesDTOToDomainMapper: MealsCategoriesDTOToDomainMapper
    ): MealsCategoryRepo = MealsCategoryRepoImpl(api, mealsCategoriesDTOToDomainMapper)


    @Provides
    fun providesMealDetailListDTOToDomainMapper() = MealDetailListDTOToDomainMapper()

    @Provides
    fun mealDetailRepo(
        api: MealsApi, mealDetailListDTOToDomainMapper: MealDetailListDTOToDomainMapper
    ): MealDetailRepo = MealDetailRepoImpl(api, mealDetailListDTOToDomainMapper)
}