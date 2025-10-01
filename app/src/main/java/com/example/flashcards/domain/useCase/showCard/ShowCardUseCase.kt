package com.example.flashcards.domain.useCase.showCard

import com.example.flashcards.data.model.Card

interface ShowCardUseCase {
    suspend fun loadDeck(cardId: Int): Card?
    fun getNextCard(): Card?
    fun resetDeck(): Card?
}