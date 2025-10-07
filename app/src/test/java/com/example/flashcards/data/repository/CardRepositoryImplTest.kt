package com.example.flashcards.data.repository

import com.example.flashcards.data.local.dao.CardDao
import com.example.flashcards.data.local.model.CardEntity
import com.example.flashcards.data.model.Card
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CardRepositoryImplTest {

    private lateinit var repository: CardRepositoryImpl
    private val dao: CardDao = mockk()

    private val card1 = Card(id = 1, first_text = "Pergunta 1", second_text = "Resposta 1")
    private val card2 = Card(id = 2, first_text = "Pergunta 2", second_text = "Resposta 2")
    private val cardsList = listOf(card1, card2)

    @Before
    fun setUp() {
        repository = CardRepositoryImpl(dao)
    }

    @Test
    fun `searchCard deve retornar lista de cards`() = runTest {
        coEvery { dao.getAllCard() } returns cardsList

        val result = repository.searchCard()

        assertEquals(cardsList, result)
        coVerify(exactly = 1) { dao.getAllCard() }
    }

    @Test(expected = Exception::class)
    fun `searchCard deve propagar excecao quando dao lancar`() = runTest {
        coEvery { dao.getAllCard() } throws Exception("Erro no DAO")

        repository.searchCard()

        coVerify(exactly = 1) { dao.getAllCard() }
    }

    @Test
    fun `addCard deve retornar true quando insercao for bem sucedida`() = runTest {
        val cardEntity = CardEntity(id = 0, first_text = "Nova Pergunta", second_text = "Nova Resposta")

        coEvery { dao.insertCard(cardEntity) } returns Unit

        val result = repository.addCard(cardEntity)

        assertEquals(true, result)
        coVerify(exactly = 1) { dao.insertCard(cardEntity) }
    }

    @Test
    fun `addCard deve retornar false quando ocorrer excecao`() = runTest {
        val cardEntity = CardEntity(id = 0, first_text = "Erro", second_text = "Teste")

        coEvery { dao.insertCard(cardEntity) } throws Exception("Erro ao inserir")

        val result = repository.addCard(cardEntity)

        assertEquals(false, result)
        coVerify(exactly = 1) { dao.insertCard(cardEntity) }
    }

    @Test
    fun `showCard deve retornar lista de cards apos o id especificado`() = runTest {
        val cardId = 1
        coEvery { dao.getCardsAfterId(cardId) } returns listOf(card2)

        val result = repository.showCard(cardId)

        assertEquals(listOf(card2), result)
        coVerify(exactly = 1) { dao.getCardsAfterId(cardId) }
    }

    @Test(expected = Exception::class)
    fun `showCard deve propagar excecao quando dao lancar`() = runTest {
        val cardId = 1
        coEvery { dao.getCardsAfterId(cardId) } throws Exception("Erro ao buscar")

        repository.showCard(cardId)

        coVerify(exactly = 1) { dao.getCardsAfterId(cardId) }
    }

    @Test
    fun `deleteCard deve retornar true quando linhas deletadas forem maiores que zero`() = runTest {
        val cardId = 1
        coEvery { dao.deleteCardId(cardId) } returns 1

        val result = repository.deleteCard(cardId)

        assertEquals(true, result)
        coVerify(exactly = 1) { dao.deleteCardId(cardId) }
    }

    @Test
    fun `deleteCard deve retornar false quando nenhuma linha for deletada`() = runTest {
        val cardId = 2
        coEvery { dao.deleteCardId(cardId) } returns 0

        val result = repository.deleteCard(cardId)

        assertEquals(false, result)
        coVerify(exactly = 1) { dao.deleteCardId(cardId) }
    }

    @Test
    fun `deleteCard deve retornar false quando ocorrer excecao`() = runTest {
        val cardId = 3
        coEvery { dao.deleteCardId(cardId) } throws Exception("Erro ao deletar")

        val result = repository.deleteCard(cardId)

        assertEquals(false, result)
        coVerify(exactly = 1) { dao.deleteCardId(cardId) }
    }
}