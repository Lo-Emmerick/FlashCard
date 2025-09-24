package com.example.flashcards.data.repository

import com.example.flashcards.data.local.dao.CardDao
import com.example.flashcards.data.local.model.CardEntity
import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.CardRepository

class CardRepositoryImpl(
    private val dao: CardDao
) : CardRepository {

    override suspend fun searchCard(): List<Card> = dao.getAllCard()

    override suspend fun addCard(card: CardEntity): Boolean {
        return try {
            dao.insertCard(card)
            true
        } catch (e: Exception){
            false
        }
    }
}