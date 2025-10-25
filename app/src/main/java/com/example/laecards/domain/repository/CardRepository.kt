package com.example.laecards.domain.repository

import com.example.laecards.data.local.model.CardEntity
import com.example.laecards.data.model.Card

interface CardRepository {
    suspend fun searchCard(): List<Card>
    suspend fun addCard(card: CardEntity): Boolean
    suspend fun showCard(cardId: Int): List<Card>
    suspend fun deleteCard(cardId: Int): Boolean
}