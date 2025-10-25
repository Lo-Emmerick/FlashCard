package com.example.laecards.presentation.ui.cardsInformation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.laecards.data.model.Card
import com.example.laecards.domain.useCase.deleteCard.DeleteCardUseCase
import com.example.laecards.domain.useCase.showCard.ShowCardUseCase
import com.example.laecards.presentation.ui.cardsInformation.state.CardsInformationDeleteState
import com.example.laecards.presentation.ui.cardsInformation.state.CardsInformationState
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

class CardsInformationViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CardsInformationViewModel
    private val showCardUseCase: ShowCardUseCase = mockk()
    private val deleteCardUseCase: DeleteCardUseCase = mockk()

    private val card1 = Card(id = 1, first_text = "Front1", second_text = "Back1")
    private val card2 = Card(id = 2, first_text = "Front2", second_text = "Back2")

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = CardsInformationViewModel(showCardUseCase, deleteCardUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadDeck deve retornar Success quando houver card`() = runTest {
        coEvery { showCardUseCase.loadDeck(1) } returns card1

        viewModel.loadDeck(1)
        advanceUntilIdle()

        assertEquals(CardsInformationState.Success(card1), viewModel.state.value)
    }

    @Test
    fun `loadDeck deve retornar Empty quando não houver card`() = runTest {
        coEvery { showCardUseCase.loadDeck(1) } returns null

        viewModel.loadDeck(1)
        advanceUntilIdle()

        assertEquals(CardsInformationState.Empty, viewModel.state.value)
    }

    @Test
    fun `loadDeck deve retornar Error quando lançar excecao`() = runTest {
        coEvery { showCardUseCase.loadDeck(1) } throws Exception()

        viewModel.loadDeck(1)
        advanceUntilIdle()

        assertEquals(CardsInformationState.Error, viewModel.state.value)
    }

    @Test
    fun `showNextCard deve retornar Success quando houver proximo card`() = runTest {
        coEvery { showCardUseCase.advanceIndex() } returns card2

        viewModel.showNextCard()
        advanceUntilIdle()

        assertEquals(CardsInformationState.Success(card2), viewModel.state.value)
    }

    @Test
    fun `showNextCard deve retornar Empty quando nao houver proximo card`() = runTest {
        coEvery { showCardUseCase.advanceIndex() } returns null

        viewModel.showNextCard()
        advanceUntilIdle()

        assertEquals(CardsInformationState.Empty, viewModel.state.value)
    }

    @Test
    fun `showNextCard deve retornar Error quando lançar excecao`() = runTest {
        coEvery { showCardUseCase.advanceIndex() } throws Exception()

        viewModel.showNextCard()
        advanceUntilIdle()

        assertEquals(CardsInformationState.Error, viewModel.state.value)
    }

    @Test
    fun `deleteCard deve retornar Success e atualizar estado quando deletar com proximo card`() = runTest {
        coEvery { deleteCardUseCase(1) } returns true
        coEvery { showCardUseCase.removeCardFromList(1) } returns Unit
        coEvery { showCardUseCase.getNextCard() } returns card2

        viewModel.deleteCard(1)
        advanceUntilIdle()

        assertEquals(CardsInformationDeleteState.Success, viewModel.deleteState.value)
        assertEquals(CardsInformationState.Success(card2), viewModel.state.value)
    }

    @Test
    fun `deleteCard deve retornar Empty quando deletar e nao houver proximo card`() = runTest {
        coEvery { deleteCardUseCase(1) } returns true
        coEvery { showCardUseCase.removeCardFromList(1) } returns Unit
        coEvery { showCardUseCase.getNextCard() } returns null

        viewModel.deleteCard(1)
        advanceUntilIdle()

        assertEquals(CardsInformationDeleteState.Empty, viewModel.deleteState.value)
    }

    @Test
    fun `deleteCard deve retornar Error quando deletar falhar`() = runTest {
        coEvery { deleteCardUseCase(1) } returns false

        viewModel.deleteCard(1)
        advanceUntilIdle()

        assertEquals(CardsInformationDeleteState.Error, viewModel.deleteState.value)
    }
}