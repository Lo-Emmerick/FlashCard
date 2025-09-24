package com.example.flashcards.domain.useCase.addCard

import com.example.flashcards.domain.data.CardEdit

interface AddCardUseCase {
    suspend operator fun invoke(card: CardEdit): Boolean
}