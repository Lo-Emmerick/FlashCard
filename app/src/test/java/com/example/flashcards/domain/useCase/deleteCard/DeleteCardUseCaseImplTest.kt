package com.example.flashcards.domain.useCase.deleteCard

import com.example.flashcards.domain.repository.CardRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteCardUseCaseImplTest {
    private lateinit var useCase: DeleteCardUseCaseImpl
    private val repository: CardRepository = mockk()

    @Before
    fun setUp() {
        useCase = DeleteCardUseCaseImpl(repository)
    }

    @Test
    fun `invoke deve retornar true quando exclusao for bem sucedida`() = runTest {
        val cardId = 1
        coEvery { repository.deleteCard(cardId) } returns true

        val result = useCase.invoke(cardId)

        assertEquals(true, result)
        coVerify(exactly = 1) { repository.deleteCard(cardId) }
    }

    @Test
    fun `invoke deve retornar false quando exclusao falhar`() = runTest {
        val cardId = 2
        coEvery { repository.deleteCard(cardId) } returns false

        val result = useCase.invoke(cardId)

        assertEquals(false, result)
        coVerify(exactly = 1) { repository.deleteCard(cardId) }
    }

    @Test
    fun `invoke deve lançar excecao quando repository lançar excecao`() = runTest {
        val cardId = 3
        coEvery { repository.deleteCard(cardId) } throws Exception("Erro ao deletar card")

        try {
            useCase.invoke(cardId)
            assert(false) // não deve chegar aqui
        } catch (e: Exception) {
            assertEquals("Erro ao deletar card", e.message)
        }

        coVerify(exactly = 1) { repository.deleteCard(cardId) }
    }
}