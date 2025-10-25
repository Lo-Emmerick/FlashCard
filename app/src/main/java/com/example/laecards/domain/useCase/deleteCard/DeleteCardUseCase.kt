package com.example.laecards.domain.useCase.deleteCard

interface DeleteCardUseCase {
    suspend operator fun invoke(cardId: Int): Boolean
}