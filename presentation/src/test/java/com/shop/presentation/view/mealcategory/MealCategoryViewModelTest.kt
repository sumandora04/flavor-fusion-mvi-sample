package com.shop.presentation.view.mealcategory

import app.cash.turbine.test
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealcategory.model.MealCategory
import com.shop.domain.feature.mealcategory.usecase.GetMealCategoriesUseCase
import com.shop.presentation.util.CoroutinesTestRule
import com.shop.presentation.util.MockDataProvider
import com.shop.presentation.view.mealcategory.mapper.MealCategoryDomainToPresentationMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MealCategoryViewModelTest {

    private val mockMealCategoryUseCase = mockk<GetMealCategoriesUseCase>()
    private lateinit var categoryMapper: MealCategoryDomainToPresentationMapper
    private lateinit var viewModel: MealCategoryViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val expectedCategoriesList = MockDataProvider.mockDomainMealsCategories

    @Before
    fun setUp() {
        categoryMapper = MealCategoryDomainToPresentationMapper()
        viewModel = MealCategoryViewModel(mockMealCategoryUseCase, categoryMapper)
    }

    @Test
    fun `fetchMealsCategories should update view state with loaded meal categories`() {
        runTest {
            // Given
            val result = Events.Success(expectedCategoriesList)
            coEvery { mockMealCategoryUseCase.invoke(Unit) } returns result

            // When
            viewModel.setIntent(CategoriesIntent.FetchMealsCategories)

            // Then
            viewModel.state.test {
                val initialEmitState = awaitItem()
                assertEquals(MealsCategoriesState.Loading, initialEmitState)

                val successState = awaitItem()
                assertEquals(successState is MealsCategoriesState.MealCategories, true)
                val emittedCategories = (successState as MealsCategoriesState.MealCategories).categories
                assertEquals(expectedCategoriesList.size, emittedCategories.size)

                cancelAndConsumeRemainingEvents()
            }
        }
    }


    @Test
    fun `fetchMealsCategories should update view state with error`() {
        runTest {
            // Given
            val errorMessage = "Something went wrong"
            val result = Events.Error<List<MealCategory>>(errorMessage)
            coEvery { mockMealCategoryUseCase.invoke(Unit) } returns result

            // When
            viewModel.setIntent(CategoriesIntent.FetchMealsCategories)

            // Then
            viewModel.state.test {
                val initialEmitState = awaitItem()
                assertEquals(MealsCategoriesState.Loading, initialEmitState)

                val errorState = awaitItem()
                assertEquals(errorState is MealsCategoriesState.Error, true)
                assertEquals(errorMessage, (errorState as MealsCategoriesState.Error).error)

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `handleIntent should invoke navigateToMealsList for OnCategoryItemClick intent`() {
        runTest {
            // Given
            val category = MockDataProvider.mockPresentationMealCategoryDetail
            val onCategoryItemClickIntent = CategoriesIntent.OnCategoryItemClick(category)

            // When
            viewModel.setIntent(onCategoryItemClickIntent)

            // Then
            viewModel.sideEffect.test {
                val sideEffect = awaitItem()
                assertEquals(sideEffect is CategorySideEffect.NavigateToMealsList, true)
                assertEquals(category, (sideEffect as CategorySideEffect.NavigateToMealsList).category)

                cancelAndConsumeRemainingEvents()
            }
        }
    }


    @Test
    fun `navigateToMealsList should emit NavigateToMealsList side effect`() {
        runTest {
            // Given
            val category = MockDataProvider.mockPresentationMealCategoryDetail

            // When
            viewModel.navigateToMealsList(category)

            // Then
            viewModel.sideEffect.test {
                val sideEffect = awaitItem()
                assertEquals(sideEffect is CategorySideEffect.NavigateToMealsList, true)
                assertEquals(category, (sideEffect as CategorySideEffect.NavigateToMealsList).category)

                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
