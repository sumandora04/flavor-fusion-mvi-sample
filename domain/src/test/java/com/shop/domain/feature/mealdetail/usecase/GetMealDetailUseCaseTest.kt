package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealDetailUseCaseTest{
    private var mockMealDetailRepo = mockk<MealDetailRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealDetailUseCase

    @Before
    fun setup() {
        useCase = GetMealDetailUseCase(mockMealDetailRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Success events with meal detail`() = runTest {
        // Given
        val mealId = "52772"
        val mockMealDetail = MockDataProvider.mockDomainMealDetail1
        coEvery { mockMealDetailRepo.getMealById(mealId) } returns mockMealDetail

        // When
        val result = useCase.invoke(mealId)

        // Then
        coVerify { mockMealDetailRepo.getMealById(mealId) }
        assertEquals(result is Events.Success, true)
        assertEquals((result as Events.Success).data, mockMealDetail)
    }

    @Test
    fun `invoke should emit Error events when repo throws an exception`() = runTest {
        // Given
        val mealId = "52772"
        val mockErrorMessage = "Error fetching meal detail"
        coEvery { mockMealDetailRepo.getMealById(mealId) } throws Exception(mockErrorMessage)

        // When
        val result = useCase.invoke(mealId)

        // Then
        coEvery { mockMealDetailRepo.getMealById(mealId) }
        assertEquals(result is Events.Error, true)
        assertEquals((result as Events.Error).message, mockErrorMessage)

    }
}