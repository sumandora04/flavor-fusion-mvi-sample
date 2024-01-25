package com.shop.data.meallist.mapper

import com.shop.data.feature.meallist.mapper.MealsListDTOToDomainMapper
import com.shop.data.util.MockDataProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MealsListDTOToDomainMapperTest {
    lateinit var SUT: MealsListDTOToDomainMapper

    @Before
    fun setup() {
        SUT = MealsListDTOToDomainMapper()
    }
    @Test
    fun `toDomainMeals should correctly transform MealsListDTO to List of Meal`() {
        // Given
        val mockMealsListDTO = MockDataProvider.mockMealsList

        // When
        val result = SUT.mealsListDTOToDomainMeals(mockMealsListDTO)

        // Then
        val expected = MockDataProvider.mockDomainMealsList
        assertEquals(expected, result)
    }
}