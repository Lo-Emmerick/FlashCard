package com.example.flashcards.domain.useCase.showCard

import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.CardRepository

class ShowCardUseCaseImpl(private val repository: CardRepository) : ShowCardUseCase {
    override suspend fun invoke(cardId: Int): List<Card> {
        return repository.showCard(cardId)
    }
}