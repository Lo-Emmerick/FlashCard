package com.example.laecards.data.repository

import com.example.laecards.data.local.dao.CardDao
import com.example.laecards.data.local.model.CardEntity
import com.example.laecards.data.model.Card
import com.example.laecards.domain.repository.CardRepository

class CardRepositoryImpl(
    private val dao: CardDao
) : CardRepository {

    override suspend fun searchCard(): List<Card> = dao.getAllCard()

    override suspend fun addCard(card: CardEntity): Boolean {
        return try {
            dao.insertCard(card)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun showCard(cardId: Int): List<Card> {
        return dao.getCardsAfterId(cardId)
    }

    override suspend fun deleteCard(cardId: Int): Boolean {
        return try {
            val rowsDeleted = dao.deleteCardId(cardId)
            rowsDeleted > 0
        } catch (e: Exception) {
            false
        }
    }
}