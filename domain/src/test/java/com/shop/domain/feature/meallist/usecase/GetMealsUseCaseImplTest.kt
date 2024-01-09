package com.shop.domain.feature.meallist.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.repo.MealsRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealsUseCaseImplTest{

    private var mockMealsRepo = mockk<MealsRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealsUseCaseImpl

    @Before
    fun setup() {
        useCase = GetMealsUseCaseImpl(mockMealsRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Loading and Success events with meals`() = runTest {
        // Given
        val category = "Chicken"
        val mockMeals = MockDataProvider.mockDomainMealsList
        coEvery { mockMealsRepo.getMealsByCategory(category) } returns mockMeals

        // When
        val events = mutableListOf<Events<List<Meal>>>()
        useCase(category).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealsRepo.getMealsByCategory(category) }

        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Success, true)
        assertEquals((events[1] as Events.Success).data, mockMeals)
    }

    @Test
    fun `invoke should emit Loading and Error events when repo throws an exception`() = runTest {
        // Given
        val category = "Chicken"
        val mockErrorMessage = "Error fetching meals"
        coEvery { mockMealsRepo.getMealsByCategory(category) } throws Exception(mockErrorMessage)

        // When
        val events = mutableListOf<Events<List<Meal>>>()
        useCase(category).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealsRepo.getMealsByCategory(category) }

        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Error, true)
        assertEquals((events[1] as Events.Error).message, mockErrorMessage)
    }
}