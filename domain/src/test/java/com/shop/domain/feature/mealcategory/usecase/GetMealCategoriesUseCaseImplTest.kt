package com.shop.domain.feature.mealcategory.usecase

import com.shop.domain.architecture.Events
import com.shop.domain.architecture.coroutinecontext.DefaultDispatchersProvider
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.repo.MealsCategoryRepo
import com.shop.domain.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealCategoriesUseCaseImplTest {

    private var mockMealsCategoryRepo = mockk<MealsCategoryRepo>()
    private var mockDispatchersProvider = DefaultDispatchersProvider()
    private lateinit var useCase: GetMealCategoriesUseCase

    @Before
    fun setup() {
        useCase = GetMealCategoriesUseCaseImpl(mockMealsCategoryRepo, mockDispatchersProvider)
    }

    @Test
    fun `invoke should emit Loading and Success events with meals categories`() = runTest {
        // Given
        val mockMealCategories = MockDataProvider.mockDomainMealsCategories
        coEvery { mockMealsCategoryRepo.getMealsCategory() } returns mockMealCategories

        // When
        val events = mutableListOf<Events<List<MealCategory>>>()
        useCase(Unit).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealsCategoryRepo.getMealsCategory() }

        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Success, true)
        assertEquals((events[1] as Events.Success).data, mockMealCategories)
    }

    @Test
    fun `invoke should emit Loading and Error events when repo throws an exception`() = runTest {
        // Given
        val mockErrorMessage = "Error fetching meals categories"
        coEvery { mockMealsCategoryRepo.getMealsCategory() } throws Exception(mockErrorMessage)

        // When
        val events = mutableListOf<Events<List<MealCategory>>>()
        useCase(Unit).collect {
            events.add(it)
        }

        // Then
        coVerify { mockMealsCategoryRepo.getMealsCategory() }
        assertEquals(events[0] is Events.Loading, true)
        assertEquals(events[1] is Events.Error, true)
        assertEquals((events[1] as Events.Error).message, mockErrorMessage)
    }
}