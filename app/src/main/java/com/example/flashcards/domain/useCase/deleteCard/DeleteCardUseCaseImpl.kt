package com.example.flashcards.domain.useCase.deleteCard

import com.example.flashcards.data.model.Card
import com.example.flashcards.domain.repository.CardRepository

class DeleteCardUseCaseImpl(private val repository: CardRepository): DeleteCardUseCase {
    override suspend fun invoke(cardId: Int): List<Card> {
        return repository.deleteCard(cardId)
    }
}