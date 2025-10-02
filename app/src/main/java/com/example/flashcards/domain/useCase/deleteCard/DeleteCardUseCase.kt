package com.example.flashcards.domain.useCase.deleteCard

interface DeleteCardUseCase {
    suspend operator fun invoke(cardId: Int): Boolean
}