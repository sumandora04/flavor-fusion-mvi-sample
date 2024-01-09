package com.shop.domain.feature.mealdetail.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.repo.MealDetailRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealDetailUseCaseImplTest{
    private var mockMealDetailRepo = mockk<MealDetailRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealDetailUseCaseImpl

    @Before
    fun setup() {
        useCase = GetMealDetailUseCaseImpl(mockMealDetailRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Loading and Success events with meal detail`() = runTest {
        // Given
        val mealId = "52772"
        val mockMealDetail = MockDataProvider.mockDomainMealDetail1
        coEvery { mockMealDetailRepo.getMealById(mealId) } returns mockMealDetail

        // When
        val events = mutableListOf<Events<MealDetail>>()
        useCase(mealId).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealDetailRepo.getMealById(mealId) }

        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Success, true)
        assertEquals((events[1] as Events.Success).data, mockMealDetail)
    }

    @Test
    fun `invoke should emit Loading and Error events when repo throws an exception`() = runTest {
        // Given
        val mealId = "52772"
        val mockErrorMessage = "Error fetching meal detail"
        coEvery { mockMealDetailRepo.getMealById(mealId) } throws Exception(mockErrorMessage)

        // When
        val events = mutableListOf<Events<MealDetail>>()
        useCase(mealId).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealDetailRepo.getMealById(mealId) }

        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Error, true)
        assertEquals((events[1] as Events.Error).message, mockErrorMessage)
    }
}