package com.shop.presentation.view.mealdetail.mapper

import com.shop.presentation.util.MockDataProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealDetailDomainToPresentationMapperTest{
    private lateinit var mealDetailMapper:MealDetailDomainToPresentationMapper

    @Before
    fun setup(){
        mealDetailMapper = MealDetailDomainToPresentationMapper()
    }

    @Test
    fun `toPresentationMealDetail should correctly transform MealDetail to List of PresentationMealDetail`() {
        // Given
        val mockMealDetail = MockDataProvider.mockDomainMealDetail1

        // When
        val result = mealDetailMapper.mealDetailToPresentationMealDetail(mockMealDetail)

        // Then
        val expected = MockDataProvider.mockPresentationMealDetail
        Assert.assertEquals(expected, result)
    }
}