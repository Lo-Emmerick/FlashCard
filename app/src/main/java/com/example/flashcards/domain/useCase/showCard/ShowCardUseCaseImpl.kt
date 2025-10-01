package com.example.flashcards.domain.useCase.showCard

import android.util.Log
import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.CardRepository

class ShowCardUseCaseImpl(
    private val repository: CardRepository
) : ShowCardUseCase {

    private var cards: List<Card> = emptyList()
    private var currentIndex: Int = 0

    override suspend fun loadDeck(cardId: Int): Card? {
        cards = repository.showCard(cardId)
        currentIndex = 0
        return cards.getOrNull(currentIndex)
    }

    override fun getNextCard(): Card? {
        if (cards.isEmpty()) return null
        currentIndex = (currentIndex + 1) % cards.size
        return cards[currentIndex]
    }

    override fun resetDeck(): Card? {
        currentIndex = 0
        return cards.getOrNull(currentIndex)
    }
}