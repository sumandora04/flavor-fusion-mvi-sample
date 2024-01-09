package com.shop.data.mealcategory.mapper

import com.shop.data.feature.mealcategory.mapper.toDomainCategories
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class MealsCategoriesDTOToDomainMapperKtTest {

    @Test
    fun `toDomainCategories should correctly transform MealsCategoriesDTO to List of MealCategory`() {
        // Given
        val mockMealsCategoriesDTO = MockDataProvider.mockMealsCategoriesDTO

        // When
        val result = mockMealsCategoriesDTO.toDomainCategories()

        // Then
        val expected = MockDataProvider.mockDomainMealsCategories
        assertEquals(expected, result)
    }
}
