package com.shop.data.mealcategory.mapper

import com.shop.data.feature.mealcategory.mapper.MealsCategoriesDTOToDomainMapper
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealsCategoriesDTOToDomainMapperTest {
    lateinit var SUT: MealsCategoriesDTOToDomainMapper

    @Before
    fun setup() {
        SUT = MealsCategoriesDTOToDomainMapper()
    }

    @Test
    fun `toDomainCategories should correctly transform MealsCategoriesDTO to List of MealCategory`() {
        // Given
        val mockMealsCategoriesDTO = MockDataProvider.mockMealsCategoriesDTO

        // When
        val result = SUT.mealsCategoriesDTOtoDomainCategories(mockMealsCategoriesDTO)

        // Then
        val expected = MockDataProvider.mockDomainMealsCategories
        assertEquals(expected, result)
    }
}
