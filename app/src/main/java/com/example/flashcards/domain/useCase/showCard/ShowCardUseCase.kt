package com.example.flashcards.domain.useCase.showCard

import com.example.flashcards.data.model.Card

interface ShowCardUseCase {
    suspend operator fun invoke(cardId: Int): List<Card>
}