package com.example.laecards.domain.useCase.deleteCard

import com.example.laecards.domain.repository.CardRepository

class DeleteCardUseCaseImpl(private val repository: CardRepository) : DeleteCardUseCase {
    override suspend fun invoke(cardId: Int): Boolean {
        return repository.deleteCard(cardId)
    }
}