package com.example.laecards.domain.useCase.addCard

import com.example.laecards.domain.data.CardEdit

interface AddCardUseCase {
    suspend operator fun invoke(card: CardEdit): Boolean
}