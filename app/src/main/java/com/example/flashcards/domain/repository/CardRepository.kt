package com.example.flashcards.domain.repository

import com.example.flashcards.data.local.model.CardEntity
import com.example.flashcards.data.model.Card

interface CardRepository {
    suspend fun searchCard(): List<Card>
    suspend fun addCard(card: CardEntity): Boolean
    suspend fun showCard(cardId: Int): List<Card>
}