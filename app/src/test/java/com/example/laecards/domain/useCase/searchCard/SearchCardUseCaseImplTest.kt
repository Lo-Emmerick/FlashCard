package com.example.laecards.domain.useCase.searchCard

import com.example.laecards.data.model.Card
import com.example.laecards.domain.repository.CardRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchCardUseCaseImplTest {
    private lateinit var useCase: SearchCardUseCaseImpl
    private val repository: CardRepository = mockk()

    private val card1 = Card(id = 1, first_text = "Pergunta 1", second_text = "Resposta 1")
    private val card2 = Card(id = 2, first_text = "Pergunta 2", second_text = "Resposta 2")
    private val cardsList = listOf(card1, card2)

    @Before
    fun setUp() {
        useCase = SearchCardUseCaseImpl(repository)
    }

    @Test
    fun `invoke deve retornar lista de cards quando repository retornar lista`() = runTest {
        coEvery { repository.searchCard() } returns cardsList

        val result = useCase.invoke()

        assertEquals(cardsList, result)
        coVerify(exactly = 1) { repository.searchCard() }
    }

    @Test
    fun `invoke deve retornar lista vazia quando repository retornar vazia`() = runTest {
        coEvery { repository.searchCard() } returns emptyList()

        val result = useCase.invoke()

        assertEquals(emptyList<Card>(), result)
        coVerify(exactly = 1) { repository.searchCard() }
    }

    @Test
    fun `invoke deve propagar excecao quando repository lancar erro`() = runTest {
        coEvery { repository.searchCard() } throws Exception("Erro ao buscar cards")

        try {
            useCase.invoke()
            assert(false)
        } catch (e: Exception) {
            assertEquals("Erro ao buscar cards", e.message)
        }

        coVerify(exactly = 1) { repository.searchCard() }
    }
}