package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.meallist.repo.MealsRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealsUseCaseTest {

    private var mockMealsRepo = mockk<MealsRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealsUseCase

    @Before
    fun setup() {
        useCase = GetMealsUseCase(mockMealsRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Success events with meals`() = runTest {
        // Given
        val category = "Chicken"
        val mockMeals = MockDataProvider.mockDomainMealsList
        coEvery { mockMealsRepo.getMealsByCategory(category) } returns mockMeals

        // When
        val result = useCase.invoke(category)

        // Then
        coVerify { mockMealsRepo.getMealsByCategory(category) }

        assertEquals(result is Events.Success, true)
        assertEquals((result as Events.Success).data, mockMeals)
    }

    @Test
    fun `invoke should emit Error events when repo throws an exception`() = runTest {
        // Given
        val category = "Chicken"
        val mockErrorMessage = "Error fetching meals"
        coEvery { mockMealsRepo.getMealsByCategory(category) } throws Exception(mockErrorMessage)

        // When
        val result = useCase.invoke(category)

        // Then
        coVerify { mockMealsRepo.getMealsByCategory(category) }

        assertEquals(result is Events.Error, true)
        assertEquals((result as Events.Error).message, mockErrorMessage)
    }
}