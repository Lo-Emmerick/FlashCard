package com.example.laecards.presentation.ui.addInformation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.laecards.domain.data.CardEdit
import com.example.laecards.domain.useCase.addCard.AddCardUseCase
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

class AddInformationViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: AddInformationViewModel
    private val addCardUseCase: AddCardUseCase = mockk()

    private val card = CardEdit(
        firstText = "Front",
        secondText = "Back"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = AddInformationViewModel(addCardUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addCard deve retornar estado Success quando useCase retornar true`() = runTest {
        coEvery { addCardUseCase(card) } returns true

        viewModel.addCard(card)
        advanceUntilIdle()

        assertEquals(AddInformationState.Success, viewModel.state.value)
    }

    @Test
    fun `addCard deve retornar estado Error quando useCase retornar false`() = runTest {
        coEvery { addCardUseCase(card) } returns false

        viewModel.addCard(card)
        advanceUntilIdle()

        assertEquals(AddInformationState.Error, viewModel.state.value)
    }

    @Test
    fun `addCard deve retornar estado Error quando useCase lançar excecao`() = runTest {
        coEvery { addCardUseCase(card) } throws Exception()

        viewModel.addCard(card)
        advanceUntilIdle()

        assertEquals(AddInformationState.Error, viewModel.state.value)
    }
}