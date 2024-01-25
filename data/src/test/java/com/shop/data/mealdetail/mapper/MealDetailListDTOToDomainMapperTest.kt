package com.shop.data.mealdetail.mapper

import com.shop.data.feature.mealdetail.mapper.MealDetailListDTOToDomainMapper
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MealDetailListDTOToDomainMapperTest{

    lateinit var SUT: MealDetailListDTOToDomainMapper

    @Before
    fun setup() {
        SUT = MealDetailListDTOToDomainMapper()
    }

    @Test
    fun `toDomainMealDetail should correctly transform MealDetailListDTO to MealDetail`() {
        // Given
        val mockMealDetailListDTO = MockDataProvider.mockMealDetailListDTO

        // When
        val result = SUT.mealDetailListDTOToDomainMealDetail(mockMealDetailListDTO)

        // Then
        val expected = MockDataProvider.mockDomainMealDetail1
        assertEquals(expected, result)
    }
}