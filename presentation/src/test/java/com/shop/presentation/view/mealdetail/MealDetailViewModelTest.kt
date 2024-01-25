package com.shop.presentation.view.mealdetail


import app.cash.turbine.test
import com.shop.domain.architecture.Events
import com.shop.domain.feature.mealdetail.model.MealDetail
import com.shop.domain.feature.mealdetail.usecase.GetMealDetailUseCase
import com.shop.presentation.util.CoroutinesTestRule
import com.shop.presentation.util.MockDataProvider
import com.shop.presentation.view.mealdetail.mapper.MealDetailDomainToPresentationMapper
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
class MealDetailViewModelTest {

    private val mockMealDetailUseCase = mockk<GetMealDetailUseCase>()
    private lateinit var mealDetailMapper:MealDetailDomainToPresentationMapper
    private lateinit var viewModel: MealDetailViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val expectedMealDetail = MockDataProvider.mockDomainMealDetail1
    private val mealId = expectedMealDetail.mealId

    @Before
    fun setUp() {
        mealDetailMapper = MealDetailDomainToPresentationMapper()
        viewModel = MealDetailViewModel(mockMealDetailUseCase, mealDetailMapper)
    }

    @Test
    fun `fetchMealDetailById should update view state with loaded meal detail`() = runTest {
        // Given
        val result = flowOf(Events.Success(expectedMealDetail))
        coEvery { mockMealDetailUseCase.invoke(mealId) } returns result

        // When
        viewModel.setIntent(MealDetailIntent.FetchMealDetailById(mealId))

        // Then
        viewModel.state.test {
            assertEquals(awaitItem(), MealsDetailState.Loading)
            val successState = awaitItem() as MealsDetailState.MealDetailSuccess
            assertEquals(
                mealDetailMapper.mealDetailToPresentationMealDetail(expectedMealDetail),
                successState.mealDetail
            )
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchMealDetailById should update view state with error when use case throws an exception`() =
        runTest {
            // Given
            val errorMessage = "Something went wrong"
            val result = flowOf(Events.Error<MealDetail>(errorMessage))
            coEvery { mockMealDetailUseCase.invoke(mealId) } returns result

            // When
            viewModel.setIntent(MealDetailIntent.FetchMealDetailById(mealId))

            // Then
            viewModel.state.test {
                assertEquals(awaitItem(), MealsDetailState.Loading)
                val errorState = awaitItem() as MealsDetailState.Error
                assertEquals(errorMessage, errorState.error)
                expectNoEvents()
                cancelAndIgnoreRemainingEvents()
            }
        }
}
