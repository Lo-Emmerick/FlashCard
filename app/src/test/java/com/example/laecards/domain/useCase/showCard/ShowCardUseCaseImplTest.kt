package com.example.laecards.domain.useCase.showCard

import com.example.laecards.data.model.Card
import com.example.laecards.domain.repository.CardRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ShowCardUseCaseImplTest {
    private lateinit var showCardUseCase: ShowCardUseCaseImpl
    private val repository: CardRepository = mockk()

    private val cards = listOf(
        Card(id = 1, first_text = "Front1", second_text = "Back1"),
        Card(id = 2, first_text = "Front2", second_text = "Back2"),
        Card(id = 3, first_text = "Front3", second_text
        = "Back3")
    )

    @Before
    fun setUp() {
        showCardUseCase = ShowCardUseCaseImpl(repository)
    }

    @Test
    fun `loadDeck deve carregar cards e retornar primeiro card`() = runTest {
        coEvery { repository.showCard(1) } returns cards

        val result = showCardUseCase.loadDeck(1)

        assertEquals(cards[0], result)
    }

    @Test
    fun `getNextCard deve retornar card atual sem avançar`() = runTest {
        coEvery { repository.showCard(1) } returns cards
        showCardUseCase.loadDeck(1)

        val result = showCardUseCase.getNextCard()

        assertEquals(cards[0], result)
    }

    @Test
    fun `advanceIndex deve avançar para próximo card e retornar ele`() = runTest {
        coEvery { repository.showCard(1) } returns cards
        showCardUseCase.loadDeck(1)

        val next1 = showCardUseCase.advanceIndex()
        val next2 = showCardUseCase.advanceIndex()
        val next3 = showCardUseCase.advanceIndex()

        assertEquals(cards[1], next1)
        assertEquals(cards[2], next2)
        assertNull(next3)
    }

    @Test
    fun `removeCardFromList deve remover card e ajustar currentIndex`() = runTest {
        coEvery { repository.showCard(1) } returns cards
        showCardUseCase.loadDeck(1)

        showCardUseCase.removeCardFromList(1)
        assertEquals(cards[1], showCardUseCase.getNextCard())

        showCardUseCase.removeCardFromList(999)
        assertEquals(cards[1], showCardUseCase.getNextCard())
    }
}