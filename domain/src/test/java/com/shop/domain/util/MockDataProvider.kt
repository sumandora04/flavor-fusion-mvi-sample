package com.shop.domain.util

import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.meallist.model.Meal

object MockDataProvider {

    /*Mock data for Domain model classes:*/
    val mockDomainMealDetail1 = MealDetail(
        mealId = "52772",
        mealName = "Teriyaki Chicken Casserole",
        mealCategory = "Chicken",
        mealArea = "Japanese",
        mealImage = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        instructions = "Cooking Instruction for Teriyaki Chicken Casserole"
    )

    val mockDomainMealDetail2 = MealDetail(
        mealId = "52771",
        mealName = "Spicy Arrabiata Penne",
        mealCategory = "Vegetarian",
        mealArea = "Italian",
        mealImage = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
        instructions = "Cooking Instruction for Spicy Arrabiata Penne"
    )

    val mockDomainMealDetailList = listOf(mockDomainMealDetail1)

    val mockDomainMeal1 = Meal(
        mealId = "52959",
        mealName = "Baked salmon with fennel & tomatoes",
        mealImage = "https://www.themealdb.com/images/media/meals/1548772327.jpg"
    )

    val mockDomainMeal2 = Meal(
        mealId = "52819",
        mealName = "Cajun spiced fish tacos",
        mealImage = "https://www.themealdb.com/images/media/meals/uvuyxu1503067369.jpg"
    )

    val mockDomainMealsList = listOf(mockDomainMeal1, mockDomainMeal2)



    val mockDomainMealCategoryDetail1 = MealCategory(
        categoryId = "2",
        category = "Chicken",
        categoryImage = "https://www.themealdb.com/images/category/chicken.png"
    )

    val mockDomainMealCategoryDetail2 = MealCategory(
        categoryId = "3",
        category = "Dessert",
        categoryImage = "https://www.themealdb.com/images/category/dessert.png"
    )

    val mockDomainMealsCategories = listOf(
        mockDomainMealCategoryDetail1,
        mockDomainMealCategoryDetail2
    )
}