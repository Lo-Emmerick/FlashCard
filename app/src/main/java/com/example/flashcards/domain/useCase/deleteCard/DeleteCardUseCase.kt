package com.example.flashcards.domain.useCase.deleteCard

import com.example.flashcards.data.model.Card

interface DeleteCardUseCase {
    suspend operator fun invoke(cardId: Int): List<Card>
}