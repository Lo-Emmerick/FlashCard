package com.example.laecards.domain.useCase.showCard

import com.example.laecards.data.model.Card
import com.example.laecards.domain.repository.CardRepository

class ShowCardUseCaseImpl(
    private val repository: CardRepository
) : ShowCardUseCase {

    private var cards: MutableList<Card> = mutableListOf()
    private var currentIndex: Int = 0

    override suspend fun loadDeck(cardId: Int): Card? {
        cards = repository.showCard(cardId).toMutableList()
        currentIndex = 0
        return cards.getOrNull(currentIndex)
    }

    override fun getNextCard(): Card? {
        return cards.getOrNull(currentIndex)
    }

    override fun advanceIndex(): Card? {
        return if (currentIndex < cards.size - 1) {
            currentIndex++
            cards[currentIndex]
        } else {
            null
        }
    }


    override fun removeCardFromList(cardId: Int) {
        val indexToRemove = cards.indexOfFirst { it.id == cardId }
        if (indexToRemove >= 0) {
            cards.removeAt(indexToRemove)
            if (currentIndex > indexToRemove) currentIndex--
        }
    }
}
