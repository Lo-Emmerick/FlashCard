package com.example.flashcards.domain.useCase.addCard

import com.example.flashcards.domain.data.CardEdit
import com.example.flashcards.domain.repository.CardRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddCardUseCaseImplTest {

    private lateinit var useCase: AddCardUseCaseImpl
    private val repository: CardRepository = mockk()

    private val cardEdit = CardEdit(
        firstText = "Pergunta Teste",
        secondText = "Resposta Teste"
    )

    @Before
    fun setUp() {
        useCase = AddCardUseCaseImpl(repository)
    }

    @Test
    fun `invoke deve retornar true quando repository adicionar card com sucesso`() = runTest {
        coEvery { repository.addCard(any()) } returns true

        val result = useCase.invoke(cardEdit)

        assertEquals(true, result)
        coVerify(exactly = 1) { repository.addCard(any()) }
    }

    @Test
    fun `invoke deve retornar false quando repository falhar ao adicionar card`() = runTest {
        coEvery { repository.addCard(any()) } returns false

        val result = useCase.invoke(cardEdit)

        assertEquals(false, result)
        coVerify(exactly = 1) { repository.addCard(any()) }
    }

    @Test
    fun `invoke deve lançar excecao quando repository lançar excecao`() = runTest {
        coEvery { repository.addCard(any()) } throws Exception("Erro ao adicionar card")

        try {
            useCase.invoke(cardEdit)
            assert(false)
        } catch (e: Exception) {
            assertEquals("Erro ao adicionar card", e.message)
        }

        coVerify(exactly = 1) { repository.addCard(any()) }
    }
}