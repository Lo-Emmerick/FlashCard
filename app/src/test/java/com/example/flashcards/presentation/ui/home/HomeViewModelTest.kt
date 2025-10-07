package com.example.flashcards.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.useCase.searchCard.SearchCardUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewModel
    private val searchCardUseCase: SearchCardUseCase = mockk()

    private val card1 = Card(id = 1, first_text = "Front1", second_text = "Back1")
    private val card2 = Card(id = 2, first_text = "Front2", second_text = "Back2")
    private val cardsList = listOf(card1, card2)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(searchCardUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchCard deve retornar Success quando houver cards`() = runTest {
        coEvery { searchCardUseCase() } returns cardsList

        viewModel.searchCard()
        advanceUntilIdle()

        assertEquals(HomeState.Success(cardsList), viewModel.state.value)
    }

    @Test
    fun `searchCard deve retornar Empty quando nao houver cards`() = runTest {
        coEvery { searchCardUseCase() } returns emptyList()

        viewModel.searchCard()
        advanceUntilIdle()

        assertEquals(HomeState.Empty, viewModel.state.value)
    }

    @Test
    fun `searchCard deve retornar Error quando lançar excecao`() = runTest {
        coEvery { searchCardUseCase() } throws Exception()

        viewModel.searchCard()
        advanceUntilIdle()

        assertEquals(HomeState.Error, viewModel.state.value)
    }

    @Test
    fun `searchCard deve setar Loading inicialmente`() = runTest {
        coEvery { searchCardUseCase() } returns cardsList

        viewModel.searchCard()

        assertEquals(HomeState.Loading, viewModel.state.value)
    }
}