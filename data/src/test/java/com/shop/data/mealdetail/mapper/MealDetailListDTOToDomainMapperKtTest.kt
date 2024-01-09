package com.shop.data.mealdetail.mapper

import com.shop.data.feature.mealdetail.mapper.toDomainMealDetail
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Test


class MealDetailListDTOToDomainMapperKtTest{
    @Test
    fun `toDomainMealDetail should correctly transform MealDetailListDTO to MealDetail`() {
        // Given
        val mockMealDetailListDTO = MockDataProvider.mockMealDetailListDTO

        // When
        val result = mockMealDetailListDTO.toDomainMealDetail()

        // Then
        val expected = MockDataProvider.mockDomainMealDetail1
        assertEquals(expected, result)
    }
}