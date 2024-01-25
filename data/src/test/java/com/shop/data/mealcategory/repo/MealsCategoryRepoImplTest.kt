package com.shop.data.mealcategory.repo

import com.shop.data.architecture.network.MealsApi
import com.shop.data.feature.mealcategory.mapper.MealsCategoriesDTOToDomainMapper
import com.shop.data.feature.mealcategory.repo.MealsCategoryRepoImpl
import com.shop.data.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealsCategoryRepoImplTest{

    private lateinit var mealsApi: MealsApi
    private lateinit var categoryMapper: MealsCategoriesDTOToDomainMapper
    private lateinit var mealsCategoryRepo: MealsCategoryRepoImpl

    @Before
    fun setUp() {
        mealsApi = mockk()
        categoryMapper = MealsCategoriesDTOToDomainMapper()
        mealsCategoryRepo = MealsCategoryRepoImpl(mealsApi, categoryMapper)
    }

    @Test
    fun `getMealsCategory should return List of MealCategory from api`() = runTest {
        // Given
        val mockMealsCategoriesDTO = MockDataProvider.mockMealsCategoriesDTO

        coEvery { mealsApi.getMealsCategory() } returns mockMealsCategoriesDTO

        // When
        val result = mealsCategoryRepo.getMealsCategory()

        // Then
        val expected = MockDataProvider.mockDomainMealsCategories
        Assert.assertEquals(expected, result)
    }
}