package com.shop.presentation.view.meals.mapper

import com.shop.presentation.util.MockDataProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealsDomainToPresentationMapperTest{
    private lateinit var mealsMapper:MealsDomainToPresentationMapper

    @Before
    fun setup(){
        mealsMapper = MealsDomainToPresentationMapper()
    }

    @Test
    fun `toPresentationMealList should correctly transform MealList to List of PresentationMeal`() {
        // Given
        val mockMeals = MockDataProvider.mockDomainMealsList

        // When
        val result = mealsMapper.mealListToPresentationMealList(mockMeals)

        // Then
        val expected = MockDataProvider.mockPresentationMealsList
        Assert.assertEquals(expected.first().mealId, result.first().mealId)
    }
}