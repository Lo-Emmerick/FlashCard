package com.example.laecards.domain.useCase.showCard

import com.example.laecards.data.model.Card

interface ShowCardUseCase {
    suspend fun loadDeck(cardId: Int): Card?
    fun getNextCard(): Card?
    fun removeCardFromList(cardId: Int)
    fun advanceIndex(): Card?
}