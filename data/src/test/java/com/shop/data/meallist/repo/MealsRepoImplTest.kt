package com.shop.data.meallist.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.meallist.mapper.MealsListDTOToDomainMapper
import com.shop.data.feature.meallist.repo.MealsRepoImpl
import com.shop.data.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MealsRepoImplTest{
    private lateinit var mealsApi: MealsApi
    private lateinit var mealsListMapper: MealsListDTOToDomainMapper
    private lateinit var mealsRepo: MealsRepoImpl

    @Before
    fun setUp() {
        mealsApi = mockk()
        mealsListMapper = MealsListDTOToDomainMapper()
        mealsRepo = MealsRepoImpl(mealsApi, mealsListMapper)
    }

    @Test
    fun `getMealsByCategory should return List of meals from api`() = runTest {
        // Given
        val category = "Seafood"
        val mockMealsDTO = MockDataProvider.mockMealsList

        coEvery { mealsApi.getMealsByCategory(category) } returns mockMealsDTO

        // When
        val result = mealsRepo.getMealsByCategory(category)

        // Then
        val expected = MockDataProvider.mockDomainMealsList
        assertEquals(expected, result)
    }
}