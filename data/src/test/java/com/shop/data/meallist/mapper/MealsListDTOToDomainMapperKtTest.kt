package com.shop.data.meallist.mapper

import com.shop.data.feature.meallist.mapper.toDomainMeals
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Test


class MealsListDTOToDomainMapperKtTest {
    @Test
    fun `toDomainMeals should correctly transform MealsListDTO to List of Meal`() {
        // Given
        val mockMealsListDTO = MockDataProvider.mockMealsList

        // When
        val result = mockMealsListDTO.toDomainMeals()

        // Then
        val expected = MockDataProvider.mockDomainMealsList
        assertEquals(expected, result)
    }
}