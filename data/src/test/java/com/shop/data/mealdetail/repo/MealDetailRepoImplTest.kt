package com.shop.data.mealdetail.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.mealdetail.repo.MealDetailRepoImpl
import com.shop.data.util.MockDataProvider
import com.shop.data.util.MockDataProvider.mockMealDetailListDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MealDetailRepoImplTest{

    private lateinit var mealsApi: MealsApi
    private lateinit var mealDetailRepo: MealDetailRepoImpl

    @Before
    fun setUp() {
        mealsApi = mockk()
        mealDetailRepo = MealDetailRepoImpl(mealsApi)
    }

    @Test
    fun `getMealById should return List of meals from api`() = runTest {
        // Given
        val mealId = "52772"

        coEvery { mealsApi.getMealDetailById(mealId) } returns mockMealDetailListDTO

        // When
        val result = mealDetailRepo.getMealById(mealId)

        // Then
        val expected = MockDataProvider.mockDomainMealDetailList.first()
        Assert.assertEquals(expected, result)
    }
}