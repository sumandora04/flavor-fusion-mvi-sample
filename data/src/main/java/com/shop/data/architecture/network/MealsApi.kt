package com.shop.data.architecture.network

import com.shop.data.feature.mealdetail.model.MealDetailListDTO
import com.shop.data.feature.meallist.model.MealsListDTO
import com.shop.data.feature.mealcategory.model.MealsCategoriesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    suspend fun getMealsCategory(): MealsCategoriesDTO

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category:String
    ): MealsListDTO

    @GET("lookup.php")
    suspend fun getMealDetailById(
        @Query("i") mealId:String
    ): MealDetailListDTO
}