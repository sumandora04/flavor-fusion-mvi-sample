package com.shop.data.util

import com.shop.data.feature.mealdetail.model.MealDetailDTO
import com.shop.data.feature.mealdetail.model.MealDetailListDTO
import com.shop.data.feature.meallist.model.MealDTO
import com.shop.data.feature.meallist.model.MealsListDTO
import com.shop.data.feature.mealcategory.model.MealCategoryDetailDTO
import com.shop.data.feature.mealcategory.model.MealsCategoriesDTO
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.mealcategory.model.MealCategory

object MockDataProvider {
    /*Mock data for Data model classes:*/
    val mockMealDetail1 = MealDetailDTO(
        mealId = "52772",
        meal = "Teriyaki Chicken Casserole",
        category = "Chicken",
        area = "Japanese",
        mealThumb = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        instructions = "Cooking Instruction for Teriyaki Chicken Casserole"
    )

    val mockMealDetail2 = MealDetailDTO(
        mealId = "52771",
        meal = "Spicy Arrabiata Penne",
        category = "Vegetarian",
        area = "Italian",
        mealThumb = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
        instructions = "Cooking Instruction for Spicy Arrabiata Penne"
    )


    val mockMealDetailListDTO: MealDetailListDTO = MealDetailListDTO(
        meals = listOf(mockMealDetail1)
    )

    val mockMeal1 = MealDTO(
        mealId = "52959",
        meal = "Baked salmon with fennel & tomatoes",
        mealThumb = "https://www.themealdb.com/images/media/meals/1548772327.jpg"
    )

    val mockMeal2 = MealDTO(
        mealId = "52819",
        meal = "Cajun spiced fish tacos",
        mealThumb = "https://www.themealdb.com/images/media/meals/uvuyxu1503067369.jpg"
    )

    val mockMealsList = MealsListDTO(
        meals = listOf(mockMeal1, mockMeal2)
    )


    val mockMealCategoryDetailDTO1 = MealCategoryDetailDTO(
        categoryId = "2",
        category = "Chicken",
        categoryDescription = "Chicken meal category description",
        categoryThumb = "https://www.themealdb.com/images/category/chicken.png"
    )

    val mockMealCategoryDetailDTO2 = MealCategoryDetailDTO(
        categoryId = "3",
        category = "Dessert",
        categoryDescription = "Dessert meal category description",
        categoryThumb = "https://www.themealdb.com/images/category/dessert.png"
    )

    val mockMealsCategoriesDTO = MealsCategoriesDTO(
        categories = listOf(mockMealCategoryDetailDTO1, mockMealCategoryDetailDTO2)
    )

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

    val mockDomainMealsCategories = listOf(mockDomainMealCategoryDetail1,
                                           mockDomainMealCategoryDetail2)
}