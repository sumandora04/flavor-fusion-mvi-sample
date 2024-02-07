package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealCategoriesUseCaseTest {

    private var mockMealsCategoryRepo = mockk<MealsCategoryRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealCategoriesUseCase

    @Before
    fun setup() {
        useCase = GetMealCategoriesUseCase(mockMealsCategoryRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Success event with meals categories`() = runTest {
        // Given
        val mockMealCategories = MockDataProvider.mockDomainMealsCategories
        coEvery { mockMealsCategoryRepo.getMealsCategory() } returns mockMealCategories

        // When
        val result = useCase.invoke(Unit)

        // Then
        coEvery { mockMealsCategoryRepo.getMealsCategory() }
        assertEquals(result is Events.Success, true)
        assertEquals((result as Events.Success).data, mockMealCategories)
    }

    @Test
    fun `invoke should emit Error event when repo throws an exception`() = runTest {
        // Given
        val mockErrorMessage = "Error fetching meals categories"
        coEvery { mockMealsCategoryRepo.getMealsCategory() } throws Exception(mockErrorMessage)

        // When
        val result = useCase.invoke(Unit)

        // Then
        coEvery { mockMealsCategoryRepo.getMealsCategory() }
        assertEquals(result is Events.Error, true)
        assertEquals((result as Events.Error).message, mockErrorMessage)
    }
}