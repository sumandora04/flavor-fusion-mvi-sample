package com.shop.presentation.view.meals

import app.cash.turbine.test
import com.shop.domain.architecture.Events
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import com.shop.presentation.util.CoroutinesTestRule
import com.shop.presentation.util.MockDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MealsByCategoryViewModelTest {

    private val mockMealsUseCase = mockk<GetMealsUseCase>()
    private lateinit var viewModel: MealsByCategoryViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val expectedMealsList = MockDataProvider.mockDomainMealsList

    @Before
    fun setUp() {
        viewModel = MealsByCategoryViewModel(mockMealsUseCase)
    }

    @Test
    fun `fetchMealsByCategory should update view state with loaded meals list`() {
        runTest {
            // Given
            val category = "Chicken"
            val result = flowOf(Events.Success(expectedMealsList))
            coEvery {
                mockMealsUseCase.invoke(category)
            } returns result

            // When
            viewModel.setIntent(MealsByCategoryIntent.FetchMealsByCategory(category))

            // Then
            viewModel.state.test {
                val initialEmitState = awaitItem()
                assertEquals(MealsByCategoryState.Loading, initialEmitState)

//                val loadingState = awaitItem()
//                assertEquals(MealsByCategoryState.Loading, loadingState)

                val successState = awaitItem()
                assertEquals(successState is MealsByCategoryState.MealsList, true)
                val emittedMeals = (successState as MealsByCategoryState.MealsList).meals
                assertEquals(expectedMealsList, emittedMeals)

                cancelAndConsumeRemainingEvents()
            }
        }
    }


    @Test
    fun `fetchMealsByCategory should update view state with error when use case throws an exception`() =
        runTest {
            // Given
            val category = "Chicken"
            val errorMessage = "Something went wrong"
            val result = flowOf(Events.Error<List<Meal>>(errorMessage))
            coEvery { mockMealsUseCase.invoke(category) } returns result

            // When
            viewModel.setIntent(MealsByCategoryIntent.FetchMealsByCategory(category))

            // Then
            viewModel.state.test {
                // Wait for Idle and Loading events
                assertEquals(awaitItem(), MealsByCategoryState.Loading)
//                assertEquals(awaitItem(), MealsByCategoryState.Loading)

                // Wait for Error state
                val errorState = awaitItem() as MealsByCategoryState.Error
                assertEquals(errorMessage, errorState.error)

                // Ensure no more events are emitted
                expectNoEvents()

                // Consume any remaining events to avoid test failures
                cancelAndIgnoreRemainingEvents()
            }
        }
}
