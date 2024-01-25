package com.shop.presentation.view.meals

import app.cash.turbine.test
import com.shop.domain.architecture.Events
import com.shop.domain.feature.meallist.model.Meal
import com.shop.domain.feature.meallist.usecase.GetMealsUseCase
import com.shop.presentation.util.CoroutinesTestRule
import com.shop.presentation.util.MockDataProvider
import com.shop.presentation.view.meals.mapper.MealsDomainToPresentationMapper
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
    private lateinit var mealsMapper:MealsDomainToPresentationMapper


    private lateinit var viewModel: MealsByCategoryViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val expectedMealsList = MockDataProvider.mockDomainMealsList

    @Before
    fun setUp() {
        mealsMapper = MealsDomainToPresentationMapper()
        viewModel = MealsByCategoryViewModel(mockMealsUseCase, mealsMapper)
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
                val successState = awaitItem()
                assertEquals(successState is MealsByCategoryState.MealsList, true)
                val emittedMeals = (successState as MealsByCategoryState.MealsList).meals
                assertEquals(expectedMealsList.size, emittedMeals.size)

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
                assertEquals(awaitItem(), MealsByCategoryState.Loading)
                val errorState = awaitItem() as MealsByCategoryState.Error
                assertEquals(errorMessage, errorState.error)
                expectNoEvents()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `handleIntent should invoke navigateToMealDetail for OnMealItemClick intent`() {
        runTest {
            // Given
            val meal = MockDataProvider.mockDomainMeal1
            val onMealItemClicked = MealsByCategoryIntent.OnMealItemClick(meal.mealId)

            // When
            viewModel.setIntent(onMealItemClicked)

            // Then
            viewModel.sideEffect.test {
                val sideEffect = awaitItem()
                assertEquals(sideEffect is MealsByCategorySideEffect.NavigateToMealDetail, true)
                assertEquals(
                    meal.mealId,
                    (sideEffect as MealsByCategorySideEffect.NavigateToMealDetail).mealId
                )

                cancelAndConsumeRemainingEvents()
            }
        }
    }


    @Test
    fun `navigateToMealDetail should emit NavigateToMealDetail side effect`() {
        runTest {
            // Given
            val meal = MockDataProvider.mockDomainMeal1

            // When
            viewModel.navigateToMealDetail(meal.mealId)

            // Then
            viewModel.sideEffect.test {
                val sideEffect = awaitItem()
                assertEquals(sideEffect is MealsByCategorySideEffect.NavigateToMealDetail, true)
                assertEquals(
                    meal.mealId,
                    (sideEffect as MealsByCategorySideEffect.NavigateToMealDetail).mealId
                )

                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
