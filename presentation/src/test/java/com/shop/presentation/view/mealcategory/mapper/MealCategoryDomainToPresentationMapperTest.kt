package com.shop.presentation.view.mealcategory.mapper

import com.shop.presentation.util.MockDataProvider
import com.shop.presentation.view.mealcategory.model.PresentationMealCategory
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealCategoryDomainToPresentationMapperTest {
    private lateinit var categoryMapper: MealCategoryDomainToPresentationMapper

    @Before
    fun setup() {
        categoryMapper = MealCategoryDomainToPresentationMapper()
    }

    @Test
    fun `toPresenterMealCategory should correctly transform MealCategory to List of PresentationMealCategory`() {
        // Given
        val mockMealsCategoriesDTO = MockDataProvider.mockDomainMealsCategories

        // When
        val result = mockMealsCategoriesDTO.map {
            categoryMapper.mealCategoryToPresenterMealCategory(it)
        }

        // Then
        val expected: List<PresentationMealCategory> =
            MockDataProvider.mockPresentationMealsCategories
        Assert.assertEquals(expected.first().categoryId, result.first().categoryId)
    }
}